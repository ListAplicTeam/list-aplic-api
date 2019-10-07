package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.StudentConverterDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.dto.StudentPublicDTO;
import br.com.ufg.listaplic.exception.StudentNotFoundException;
import br.com.ufg.listaplic.repository.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LoginService {

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    public StudentPublicDTO authenticate(StudentDTO credentials) {
        StudentDTO studentDTO = studentJpaRepository.findByEmail(credentials.getEmail())
                .map(StudentConverterDTO::fromDomainToDTO)
                .orElse(null);

        if (studentDTO == null) {
            return null;
        }

        if (studentDTO.getPassword().equals(md5(credentials.getPassword()))) {
            return studentDTO;
        }

        return null;
    }

    public static String md5(String senha){
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        return sen;
    }
}
