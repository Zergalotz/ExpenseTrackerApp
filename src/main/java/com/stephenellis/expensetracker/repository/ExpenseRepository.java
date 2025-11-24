package com.stephenellis.expensetracker.repository;

import com.stephenellis.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category.name = :categoryName")
    BigDecimal getTotalByCategory(String categoryName);
}
