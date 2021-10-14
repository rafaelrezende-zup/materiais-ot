package br.com.zup.forum.controller;

import br.com.zup.forum.controller.dto.DetalheTopicoDTO;
import br.com.zup.forum.controller.dto.NewTopicoDTO;
import br.com.zup.forum.controller.dto.TopicoDTO;
import br.com.zup.forum.controller.dto.UpdateTopicoDTO;
import br.com.zup.forum.modelo.Topico;
import br.com.zup.forum.repository.CursoRepository;
import br.com.zup.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/topico")
@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDTO> getTopicos(@RequestParam(required = false) String nomeCurso, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pg) {
        Page<Topico> topicoList;
        if (Objects.isNull(nomeCurso)) {
            topicoList = topicoRepository.findAll(pg);
        } else {
            topicoList = topicoRepository.findByCursoNome(nomeCurso, pg);
        }
        return TopicoDTO.convert(topicoList);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> cadastra(@RequestBody @Valid NewTopicoDTO topicoDTO) {
        Topico topico = topicoDTO.convert(cursoRepository);
        topicoRepository.save(topico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheTopicoDTO> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        return topico.map(value -> ResponseEntity.ok(new DetalheTopicoDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateTopicoDTO updateTopicoDTO) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topico.get().setTitulo(updateTopicoDTO.getTitulo());
            topico.get().setMensagem(updateTopicoDTO.getMensagem());
            return ResponseEntity.ok(new TopicoDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover (@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
