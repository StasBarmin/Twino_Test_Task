package barmin.twino.loans.core.domain

import org.springframework.data.jpa.repository.JpaRepository

interface LoanRepository : JpaRepository<LoanEntity, Long> {
}