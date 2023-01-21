package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Shabdanov Ilim
 **/
public class Job {
    private Long id;
    private String position;//("Mentor","Management","Instructor") ушундай маанилер берилсин
    private String profession;//("Java","JavaScript")
    private String description;//("Backend developer","Fronted developer")
    private int experience;//(1,2,3........) опыт работы

    public Job(String position, String profession, String description, int experience) {
        this.position = position;
        this.profession = profession;
        this.description = description;
        this.experience = experience;
    }

    public Job(Long id, String position, String profession,int experience) {
        this.id = id;
        this.position = position;
        this.profession = profession;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "\n  Job :" +
                "\nid=" + id +
                "\nposition='" + position +
                "\nprofession='" + profession +
                "\ndescription='" + description +
                "\nexperience=" + experience +
                "\n++++++++++++++++++++++++++\n";
    }
}
