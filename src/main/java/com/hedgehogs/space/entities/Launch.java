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
@Table(name = "launches")
public class Launch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rocktid")
    private String rocktid;

    @Column(name = "launchyear")
    private String launchyear;

    @Column(name = "missionname")
    private String missionname;

    @Column(name = "links")
    private String finalline;

}
