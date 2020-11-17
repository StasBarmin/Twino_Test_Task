package barmin.twino.loans.core.utils

import barmin.twino.loans.api.dto.LoanCreateRequest
import barmin.twino.loans.api.dto.LoanDTO
import barmin.twino.loans.core.domain.LoanEntity

fun LoanCreateRequest.mapToEntity() = LoanEntity(
    personalId = null,
    firstName = firstName,
    lastName = lastName,
    birthDate = birthDate,
    employer = employer,
    salary = salary,
    liability = liability,
    requestedAmount = requestedAmount,
    requestedTerm = requestedTerm
)

fun LoanEntity.mapToDTO() = LoanDTO(
    personalId = personalId!!,
    firstName = firstName,
    lastName = lastName,
    birthDate = birthDate,
    employer = employer,
    salary = salary,
    liability = liability,
    requestedAmount = requestedAmount,
    requestedTerm = requestedTerm,
    status = status
)