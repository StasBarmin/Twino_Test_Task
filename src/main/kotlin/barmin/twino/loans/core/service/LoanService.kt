package barmin.twino.loans.core.service

import barmin.twino.loans.api.dto.LoanCreateRequest
import barmin.twino.loans.api.dto.LoanDTO
import barmin.twino.loans.api.dto.LoanStatus
import barmin.twino.loans.api.dto.ScoreDTO
import barmin.twino.loans.core.commons.ServiceException
import barmin.twino.loans.core.commons.SortParams
import barmin.twino.loans.core.domain.LoanEntity
import barmin.twino.loans.core.domain.LoanRepository
import barmin.twino.loans.core.utils.mapToDTO
import barmin.twino.loans.core.utils.mapToEntity
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class LoanService(
    private val loanRepository: LoanRepository,
    private val scoreService: ScoreService
) {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    fun createLoan(createRequest: LoanCreateRequest) =
        loanRepository.saveAndFlush(createRequest.mapToEntity()).mapToDTO()

    @Transactional(readOnly = true)
    fun getList(sortParams: SortParams?): List<LoanDTO> {
        val loans = sortParams?.sortField?.let {
            loanRepository.findAll(
                Sort.by(sortParams.sortDirection?.let { Sort.Direction.valueOf(it) } ?: Sort.Direction.DESC, it)
            )
        } ?: loanRepository.findAll()

        return loans.map(LoanEntity::mapToDTO)
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    fun deleteLoan(loanId: Long) = loanRepository.deleteById(loanId)

    //TODO process with ControllerAdvise
    @Transactional(isolation = Isolation.READ_COMMITTED)
    fun setStatus(loanId: Long, status: String) {
        val loanOpt = loanRepository.findById(loanId)
        if (loanOpt.isPresent) {
            val loanEntity = loanOpt.get()
            loanEntity.status = LoanStatus.valueOf(status)
            loanRepository.saveAndFlush(loanEntity)
        } else {
            throw ServiceException("Loan with id = $loanId doesn't exist")
        }
    }

    //TODO process with ControllerAdvise
    fun score(loanId: Long): ScoreDTO {
        val loanOpt = loanRepository.findById(loanId)
        if (loanOpt.isPresent) {
            val loanEntity = loanOpt.get()
            return scoreService.scoreLoan(loanEntity)
        } else {
            throw ServiceException("Loan with id = $loanId doesn't exist")
        }
    }
}