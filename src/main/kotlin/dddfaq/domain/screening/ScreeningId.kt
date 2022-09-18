package dddfaq.domain.screening

import java.util.UUID

data class ScreeningId(val value: String) {
  constructor() : this(UUID.randomUUID().toString())
}