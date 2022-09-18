package dddfaq.domain.screening

import java.time.LocalDateTime

data class Interview(
  val phase: Int,
  val dateTime: LocalDateTime
)