package com.mikali.weathermemoir.viewmodel

import androidx.lifecycle.ViewModel
import com.mikali.weathermemoir.model.Questionnaire
import io.reactivex.subjects.BehaviorSubject
import kotlin.random.Random

class QuestionnaireViewModel() : ViewModel() {

    private val behaviorSubject: BehaviorSubject<MutableState> = BehaviorSubject.create()
    val questionnaireObservable = behaviorSubject

    private val currentMutableState
        get() = behaviorSubject.value ?: MutableState(question = "", thought = "")

    data class MutableState(
        val question: String,
        val thought: String
    )

    init {
        behaviorSubject.onNext(currentMutableState.copy(question = Questionnaire.questions[randomIndex()]))
    }

    private fun randomIndex() = Random.nextInt(Questionnaire.questions.size)

    fun onTextFieldChange(thought: String) {
        behaviorSubject.onNext(currentMutableState.copy(thought = thought))
    }

    fun onToggleClick() {
        behaviorSubject.onNext(currentMutableState.copy(question = Questionnaire.questions[randomIndex()]))
    }
}
