package stud.fhcampuswien.ac.at.carRental.service.exception;

public class EntityNotFoundException extends RuntimeException { 

    public EntityNotFoundException(Long id, Class<?> entity) {
            super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
    }

    public EntityNotFoundException(String error) {
        super(error);
    }

}