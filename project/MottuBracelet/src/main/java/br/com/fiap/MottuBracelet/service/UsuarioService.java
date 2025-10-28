package br.com.fiap.MottuBracelet.service;

import br.com.fiap.MottuBracelet.model.Role;
import br.com.fiap.MottuBracelet.model.Usuario;
import br.com.fiap.MottuBracelet.repository.RoleRepository;
import br.com.fiap.MottuBracelet.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, 
                         RoleRepository roleRepository, 
                         PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario criarUsuario(Usuario usuario) {
        validarUsuarioUnico(usuario);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        // Adicionar role padrão se não tiver nenhuma
        if (usuario.getRoles().isEmpty()) {
            Role roleOperador = roleRepository.findByName("OPERADOR")
                    .orElseThrow(() -> new RuntimeException("Role OPERADOR não encontrada"));
            usuario.addRole(roleOperador);
        }
        
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setUsername(usuarioAtualizado.getUsername());
                    usuario.setEmail(usuarioAtualizado.getEmail());
                    usuario.setEnabled(usuarioAtualizado.getEnabled());
                    
                    // Atualizar senha apenas se foi fornecida
                    if (usuarioAtualizado.getPassword() != null && !usuarioAtualizado.getPassword().isEmpty()) {
                        usuario.setPassword(passwordEncoder.encode(usuarioAtualizado.getPassword()));
                    }
                    
                    // Atualizar roles
                    usuario.setRoles(usuarioAtualizado.getRoles());
                    
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private void validarUsuarioUnico(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("Username já existe: " + usuario.getUsername());
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já existe: " + usuario.getEmail());
        }
    }

    public List<Role> listarTodasRoles() {
        return roleRepository.findAll();
    }
}