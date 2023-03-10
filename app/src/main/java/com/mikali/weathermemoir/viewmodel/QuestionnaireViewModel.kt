package com.mikali.weathermemoir.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mikali.weathermemoir.model.Questionnaire
import com.mikali.weathermemoir.model.UserInfo
import com.mikali.weathermemoir.repository.DatabaseRepository
import com.mikali.weathermemoir.repository.WeatherRepository
import com.mikali.weathermemoir.util.Constants
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlin.random.Random

class QuestionnaireViewModel(
    private val databaseRepository: DatabaseRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val behaviorSubject: BehaviorSubject<MutableState> = BehaviorSubject.create()
    val questionnaireObservable = behaviorSubject

    private val currentMutableState
        get() = behaviorSubject.value ?: MutableState(firstName = "", question = "", thought = "", readOnly = false)

    data class MutableState(
        val firstName: String,
        val question: String,
        val thought: String,
        val readOnly: Boolean
    )

    init {
        behaviorSubject.onNext(currentMutableState.copy(firstName = "Dear Megan, ", question = Questionnaire.questions[randomIndex()]))
    }

    private fun randomIndex() = Random.nextInt(Questionnaire.questions.size)

    fun onTextFieldChange(thought: String) {
        behaviorSubject.onNext(currentMutableState.copy(thought = thought))
    }

    fun onToggleClick() {
        behaviorSubject.onNext(currentMutableState.copy(question = Questionnaire.questions[randomIndex()], thought = "", readOnly = false))
    }

    fun onSaveClick() {
        compositeDisposable.add(
            Single.zip(
                weatherRepository.getWeather(lat = "39.9612", lon = "-82.9988").firstOrError(),
                databaseRepository.getUser()
            ) { weather, user ->
                val firstWeather = weather.weather?.get(0)
                UserInfo(
                    firestoreUid = "",
                    authUid = user.uid,
                    firstName = user.firstname,
                    lastName = user.lastname ?: "",
                    memoirList = listOf(
                        UserInfo.Memoir(
                            question = currentMutableState.question,
                            thought = currentMutableState.thought,
                            creationTime = "",
                            mainWeatherConditionIcon = firstWeather?.icon ?: "",
                            mainWeatherConditionDescription = firstWeather?.main ?: ""

                        )
                    )
                )
            }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    {
                        // save thought entry to local sqlDelight database
                        it.authUid?.let { authUid ->
                            it.memoirList?.get(0)?.let { memoir ->
                                databaseRepository.saveThoughtEntries(
                                    uid = authUid,
                                    question = currentMutableState.question,
                                    thought = currentMutableState.thought,
                                    mainWeatherConditionIcon = memoir.mainWeatherConditionIcon,
                                    mainWeatherConditionDescription = memoir.mainWeatherConditionDescription
                                )
                            }
                        }
                        behaviorSubject.onNext(currentMutableState.copy(readOnly = true))
                    },
                    {
                        Log.e(Constants.QUESTIONNAIRE_TAG, "Error emitting main weather condition description", it)
                    }
                )
        )
    }

    fun onClearClick() {
        behaviorSubject.onNext(currentMutableState.copy(thought = "", readOnly = false))
    }

    fun onEditClick() {
        behaviorSubject.onNext(currentMutableState.copy(readOnly = false))
    }
}
