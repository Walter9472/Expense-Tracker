package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Long id;
    private String title;             // z. B. "Einkauf im Supermarkt"
    private BigDecimal amount;
    private LocalDate date;
    private String type;              // "EXPENSE" oder "INCOME"
    private String description;       // optionaler Text
    private Category category;        // Beziehung zu Category
    private Users user;                // Beziehung zu User
}
