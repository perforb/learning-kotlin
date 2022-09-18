package dddfaq.domain.screening

import java.util.*

data class PositionId(val value: String) {
  constructor() : this(UUID.randomUUID().toString())
}