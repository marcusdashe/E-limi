package elimi.africa.util


import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.util.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.DurationUnit

@Composable
fun rememberDatePickerDialog(
    @StringRes title: Int,
    select: Date? = null,
    bounds: CalendarConstraints? = null,
    onDateSelected: (Date) -> Unit = {},
): MaterialDatePicker<Long> {
    val datePicker = remember {
        MaterialDatePicker.Builder.datePicker()
            .setSelection((select?.time
                ?: Date().time) + 24.hours.toLong(DurationUnit.MILLISECONDS))
            .setCalendarConstraints(bounds)
            .setTitleText(title)
            .build()
    }

    DisposableEffect(datePicker) {
        val listener = MaterialPickerOnPositiveButtonClickListener<Long> {
            if (it != null) onDateSelected(Date(it))
        }
        datePicker.addOnPositiveButtonClickListener(listener)
        onDispose {
            datePicker.removeOnPositiveButtonClickListener(listener)
        }
    }

    return datePicker
}


@Composable
fun rememberFragmentManager(): FragmentManager {
    val context = LocalContext.current
    return remember(context) {
        (context as FragmentActivity).supportFragmentManager
    }
}