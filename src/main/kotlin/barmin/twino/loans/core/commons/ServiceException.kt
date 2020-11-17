package barmin.twino.loans.core.commons

import org.springframework.core.NestedRuntimeException

class ServiceException(message: String) : NestedRuntimeException(message) {
}