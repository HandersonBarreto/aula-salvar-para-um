package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.PersonDTO;
import com.devsuperior.aula.entities.Department;
import com.devsuperior.aula.entities.Person;
import com.devsuperior.aula.repositories.DepartmentRepository;
import com.devsuperior.aula.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private  PersonRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public PersonDTO insert (PersonDTO dto){
        // Cria uma nova instância da entidade Person
        Person entity = new Person();

        // Copia os dados básicos do DTO para a entidade
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());

        // Obtém uma referência gerenciada (managed) do Department pelo ID.
        // Isso evita a criação de uma nova instância manual e permite que o JPA gerencie o ciclo de vida da entidade.
        Department department = departmentRepository.getReferenceById(dto.getDepartmentId());

/*
    // Alternativa usando uma instância transient (não gerenciada pelo JPA)
    // Cria um novo objeto Department apenas com o ID, sem buscar no banco.
    // Útil quando se deseja evitar uma consulta, mas pode causar problemas
    // se o ID não existir ou se o contexto exigir um estado managed da entidade.

    Department department = new Department();
    department.setId(dto.getDepartmentId());
*/
        // Associa o Department à entidade Person
        entity.setDepartment(department);

        // Salva a entidade Person no banco de dados
        entity = repository.save(entity);

        // Retorna o DTO com os dados persistidos
        return new PersonDTO(entity);

    }
}
