package barmin.twino.loans.core.rest

import barmin.twino.loans.api.LoansApi
import barmin.twino.loans.api.dto.LoanCreateRequest
import barmin.twino.loans.api.dto.LoanDTO
import barmin.twino.loans.core.commons.SortParams
import barmin.twino.loans.core.service.LoanService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("loans")
@Validated
class LoansController(
    private val loanService: LoanService
) : LoansApi{
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    override fun createLoanApplication(@Valid @RequestBody createRequest: LoanCreateRequest) = loanService.createLoan(createRequest)

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    override fun getList(sortParams: SortParams?): List<LoanDTO> = loanService.getList(sortParams)

    @DeleteMapping("/{loanId}")
    @PreAuthorize("hasRole('ADMIN')")
    override fun deleteLoan(@PathVariable loanId: Long) = loanService.deleteLoan(loanId)

    @PutMapping("/{loanId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    override fun setStatus(@PathVariable loanId: Long, status: String) = loanService.setStatus(loanId, status)

    @GetMapping("/{loanId}/score")
    @PreAuthorize("hasRole('ADMIN')")
    override fun score(@PathVariable loanId: Long) = loanService.score(loanId)
}