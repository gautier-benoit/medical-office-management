package miage.gautier.patient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String dateNaissance;
}