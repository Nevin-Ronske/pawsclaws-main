package wcci.org.pawsclaws.DTO;

import wcci.org.pawsclaws.Enums.PetType;

public class AdmissionsDTO extends ErrorDataDTO {
    private String name;
    private Integer age;
    private PetType petType;

    public AdmissionsDTO(String name, Integer age, PetType petType) {
        this.name = name;
        this.age = age;
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

}
