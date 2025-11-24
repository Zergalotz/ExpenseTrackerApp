package com.stephenellis.expensetracker.service;

import com.stephenellis.expensetracker.exception.ResourceNotFoundException;
import com.stephenellis.expensetracker.model.Category;
import com.stephenellis.expensetracker.model.Expense;
import com.stephenellis.expensetracker.model.User;
import com.stephenellis.expensetracker.repository.CategoryRepository;
import com.stephenellis.expensetracker.repository.ExpenseRepository;
import com.stephenellis.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          CategoryRepository categoryRepository,
                          UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));
    }

    @Transactional
    public Expense createExpense(Expense expense) {
        if (expense.getUser() == null || expense.getUser().getId() == null) {
            throw new IllegalArgumentException("User must be provided with an id.");
        }
        if (expense.getCategory() == null || expense.getCategory().getId() == null) {
            throw new IllegalArgumentException("Category must be provided with an id.");
        }

        // Fetch user and category directly via repositories
        User user = userRepository.findById(expense.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + expense.getUser().getId()));

        Category category = categoryRepository.findById(expense.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + expense.getCategory().getId()));

        expense.setUser(user);
        expense.setCategory(category);

        return expenseRepository.save(expense);
    }

    @Transactional
    public Expense updateExpense(Long id, Expense incoming) {
        Expense existing = getExpenseById(id);

        if (incoming.getCategory() != null) {
            Category cat = incoming.getCategory();
            if (cat.getId() != null) {
                Category resolved = categoryRepository.findById(cat.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + cat.getId()));
                existing.setCategory(resolved);
            }
        }

        if (incoming.getUser() != null && incoming.getUser().getId() != null) {
            User u = userRepository.findById(incoming.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + incoming.getUser().getId()));
            existing.setUser(u);
        }

        existing.setDescription(incoming.getDescription());
        existing.setAmount(incoming.getAmount());
        existing.setDate(incoming.getDate());

        return expenseRepository.save(existing);
    }

    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    public BigDecimal getTotalByCategory(String categoryName) {
        return expenseRepository.getTotalByCategory(categoryName);
    }
}
