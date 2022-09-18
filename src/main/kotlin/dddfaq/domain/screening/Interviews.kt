package dddfaq.domain.screening

import java.time.LocalDateTime

data class Interviews(val value: List<Interview>) {

  fun addInterview(interviewDateTime: LocalDateTime): Interviews {
    val newInterview = Interview(
      phase = this.value.size + 1,
      dateTime = interviewDateTime
    )
    return Interviews(this.value + newInterview)
  }

  companion object {
    fun empty() = Interviews(emptyList())
  }
}