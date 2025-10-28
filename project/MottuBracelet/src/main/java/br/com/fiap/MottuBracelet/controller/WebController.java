package br.com.fiap.MottuBracelet.controller;

import br.com.fiap.MottuBracelet.model.*;
import br.com.fiap.MottuBracelet.service.*;
import br.com.fiap.MottuBracelet.util.StatusDispositivo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class WebController {

    private final PatioService patioService;
    private final MotoService motoService;
    private final DispositivoService dispositivoService;
    private final HistoricoPatioService historicoPatioService;
    private final UsuarioService usuarioService;

    public WebController(PatioService patioService, 
                        MotoService motoService, 
                        DispositivoService dispositivoService,
                        HistoricoPatioService historicoPatioService,
                        UsuarioService usuarioService) {
        this.patioService = patioService;
        this.motoService = motoService;
        this.dispositivoService = dispositivoService;
        this.historicoPatioService = historicoPatioService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("totalPatios", patioService.listarTodosPatios().size());
        model.addAttribute("totalMotos", motoService.listarTodasMotos().size());
        model.addAttribute("totalDispositivos", dispositivoService.listarTodosDispositivos().size());
        model.addAttribute("totalHistoricos", historicoPatioService.listarTodos().size());
        return "dashboard/index";
    }

    // === PÁTIOS ===
    @GetMapping("/patios")
    public String listarPatios(Model model) {
        model.addAttribute("patios", patioService.listarTodosPatios());
        return "patios/list";
    }

    @GetMapping("/patios/novo")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
    public String novoPatioForm(Model model) {
        model.addAttribute("patio", new Patio());
        return "patios/form";
    }

    @PostMapping("/patios")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
    public String salvarPatio(@Valid @ModelAttribute Patio patio, 
                             BindingResult result, 
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patios/form";
        }
        
        patioService.criarPatio(patio);
        redirectAttributes.addFlashAttribute("successMessage", "Pátio criado com sucesso!");
        return "redirect:/patios";
    }

    @GetMapping("/patios/{id}/editar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
    public String editarPatioForm(@PathVariable Long id, Model model) {
        Patio patio = patioService.buscarPatioPorId(id)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));
        model.addAttribute("patio", patio);
        return "patios/form";
    }

    @PostMapping("/patios/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
    public String atualizarPatio(@PathVariable Long id, 
                                @Valid @ModelAttribute Patio patio, 
                                BindingResult result, 
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patios/form";
        }
        
        patioService.atualizarPatio(id, patio);
        redirectAttributes.addFlashAttribute("successMessage", "Pátio atualizado com sucesso!");
        return "redirect:/patios";
    }

    @PostMapping("/patios/{id}/deletar")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletarPatio(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        patioService.deletarPatio(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pátio deletado com sucesso!");
        return "redirect:/patios";
    }

    // === MOTOS ===
    @GetMapping("/motos")
    public String listarMotos(Model model) {
        List<Moto> motos = motoService.listarTodasMotos();
        model.addAttribute("motos", motos);
        return "motos/list";
    }

    @GetMapping("/motos/nova")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
    public String novaMotoForm(Model model) {
        model.addAttribute("moto", new Moto());
        model.addAttribute("patios", patioService.listarTodosPatios());
        model.addAttribute("dispositivos", dispositivoService.listarTodosDispositivos());
        return "motos/form";
    }

    @PostMapping("/motos")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
    public String salvarMoto(@Valid @ModelAttribute Moto moto, 
                            BindingResult result, 
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("patios", patioService.listarTodosPatios());
            model.addAttribute("dispositivos", dispositivoService.listarTodosDispositivos());
            return "motos/form";
        }
        
        motoService.criarMoto(moto);
        redirectAttributes.addFlashAttribute("successMessage", "Moto criada com sucesso!");
        return "redirect:/motos";
    }

    // === DISPOSITIVOS ===
    @GetMapping("/dispositivos")
    public String listarDispositivos(Model model) {
        model.addAttribute("dispositivos", dispositivoService.listarTodosDispositivos());
        model.addAttribute("statusOptions", StatusDispositivo.values());
        return "dispositivos/list";
    }

    @GetMapping("/dispositivos/novo")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
    public String novoDispositivoForm(Model model) {
        model.addAttribute("dispositivo", new Dispositivo());
        model.addAttribute("statusOptions", StatusDispositivo.values());
        model.addAttribute("patios", patioService.listarTodosPatios());
        return "dispositivos/form";
    }

    @PostMapping("/dispositivos")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
    public String salvarDispositivo(@Valid @ModelAttribute Dispositivo dispositivo, 
                                   BindingResult result, 
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("statusOptions", StatusDispositivo.values());
            model.addAttribute("patios", patioService.listarTodosPatios());
            return "dispositivos/form";
        }
        
        dispositivoService.criarDispositivo(dispositivo);
        redirectAttributes.addFlashAttribute("successMessage", "Dispositivo criado com sucesso!");
        return "redirect:/dispositivos";
    }

    // === HISTÓRICOS ===
    @GetMapping("/historicos")
    public String listarHistoricos(Model model) {
        model.addAttribute("historicos", historicoPatioService.listarTodos());
        return "historicos/list";
    }

    // === USUÁRIOS (ADMIN APENAS) ===
    @GetMapping("/admin/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodosUsuarios());
        return "admin/usuarios/list";
    }

    @GetMapping("/admin/usuarios/novo")
    @PreAuthorize("hasRole('ADMIN')")
    public String novoUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", usuarioService.listarTodasRoles());
        return "admin/usuarios/form";
    }

    @PostMapping("/admin/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public String salvarUsuario(@Valid @ModelAttribute Usuario usuario, 
                               BindingResult result, 
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("roles", usuarioService.listarTodasRoles());
            return "admin/usuarios/form";
        }
        
        usuarioService.criarUsuario(usuario);
        redirectAttributes.addFlashAttribute("successMessage", "Usuário criado com sucesso!");
        return "redirect:/admin/usuarios";
    }
}