package com.mikali.weathermemoir.util

import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object Constants {
    const val API_KEY = "2393f5f9639467c6131b3cdf4b2e7516"
    const val HOME_TAG = "HomeViewModel"
    const val LOGIN_TAG = "LoginViewModel"
    const val SIGNUP_TAG = "SignupViewModel"
    const val QUESTIONNAIRE_TAG = "QuestionnaireViewModel"
    const val MEMOIR_LIST_TAG = "MemoirListViewModel"
    const val MAIN_TAG = "MainViewModel"

    // ex. 3/26/22, 10:00 AM
    val dateTimeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
}
