package com.utpconnect.groups_service.service;

import com.utpconnect.groups_service.config.RabbitMQConfig;
import com.utpconnect.groups_service.model.Groups;
import com.utpconnect.groups_service.repository.GroupRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    // Obtener un grupo por su ID
    public Groups getGroupById(Long id) {
        Optional<Groups> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent())
            return groupOptional.get();
        else
            throw new RuntimeException("Grupo no encontrado");
    }

//    // Obtener un grupo por su nombre
//    public Groups getGroupByName(String name) {
//        Optional<Groups> groupOptional = groupRepository.findByName(name);
//        if (groupOptional.isPresent())
//            return groupOptional.get();
//        else
//            throw new RuntimeException("No se encontró el grupo con el nombre: " + name);
//    }

    // Crear un nuevo grupo
    public Groups createGroup(Groups group) {
        return groupRepository.save(group);
    }

//    // Actualizar un grupo existente
//    public Groups updateGroup(Long id, Groups groupDetails) {
//        Groups group = groupRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
//
//        group.setName(groupDetails.getName());
//        group.setDescription(groupDetails.getDescription());
//        // Agrega más atributos si es necesario
//
//        return groupRepository.save(group);
//    }

    // Eliminar un grupo
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    // RabbitMQ Listener para recibir mensajes relacionados a grupos
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(Groups group) {
        // Lógica para guardar el grupo en la base de datos
        groupRepository.save(group);
        System.out.println("Grupo registrado: " + group.getName());
    }
}
