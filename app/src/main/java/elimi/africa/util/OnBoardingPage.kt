package elimi.africa.util

import androidx.annotation.DrawableRes
import elimi.africa.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val description: String,
    val title : String
){
    object First: OnBoardingPage(
        image = R.drawable.capentry,
        title = "E-limi LMS",
        description = "We provide the best learning courses at affordable price"
    )

    object Second: OnBoardingPage(
        image = R.drawable.electrician,
        title = "Learn and Earn",
        description = "Enroll into one of our programmes to acquire a skill"
    )

    object Third: OnBoardingPage(
        image = R.drawable.webdesigner,
        title = "Enhance your Life ",
        description = "Take this great opportunity to earn and be self-reliant"
    )
}
