package barmin.twino.loans

import barmin.twino.loans.api.dto.LoanCreateRequest
import barmin.twino.loans.api.dto.LoanStatus
import barmin.twino.loans.core.commons.SortParams
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class LoansServiceTest : LoansApplicationTest() {

    @Test
    fun createLoan() {
        val createRequest = LoanCreateRequest(
            firstName = "test",
            lastName = "test",
            birthDate = LocalDate.now(),
            employer = "test",
            salary = BigDecimal.TEN,
            liability = BigDecimal.ONE,
            requestedAmount = BigDecimal(1000),
            requestedTerm = 5L
        )
        val sortParams = SortParams("lastName", "ASC")
        loanService.createLoan(createRequest)
        val loans = loanService.getList(sortParams)
        Assertions.assertFalse(loans.isEmpty())
    }

    @Test
    fun setStatus() {
        val createRequest = LoanCreateRequest(
            firstName = "test",
            lastName = "test",
            birthDate = LocalDate.now(),
            employer = "test",
            salary = BigDecimal.TEN,
            liability = BigDecimal.ONE,
            requestedAmount = BigDecimal(1000),
            requestedTerm = 5L
        )
        val sortParams = SortParams("lastName", "ASC")
        loanService.createLoan(createRequest)
        var loans = loanService.getList(sortParams)
        Assertions.assertNull(loans[0].status)
        loanService.setStatus(loans[0].personalId, LoanStatus.APPROVED.name)
        loans = loanService.getList(sortParams)
        Assertions.assertEquals(LoanStatus.APPROVED.name, loans[0].status?.name)
    }
}