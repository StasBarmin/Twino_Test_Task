package barmin.twino.loans.api

import barmin.twino.loans.api.dto.LoanCreateRequest
import barmin.twino.loans.api.dto.LoanDTO
import barmin.twino.loans.api.dto.ScoreDTO
import barmin.twino.loans.core.commons.SortParams

//TODO add swagger annotations
interface LoansApi {

    fun createLoanApplication(createRequest: LoanCreateRequest): LoanDTO

    fun getList(sortParams: SortParams?): List<LoanDTO>

    fun deleteLoan(loanId: Long)

    fun setStatus(loanId: Long, status: String)

    fun score(loanId: Long): ScoreDTO
}