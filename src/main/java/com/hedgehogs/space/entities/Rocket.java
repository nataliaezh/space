package com.hedgehogs.space.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author natalya_ezhkova@mail.ru
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rockets")
public class Rocket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rocketid")
    private String rocketid;
}
