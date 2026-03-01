package com.lucca.sitecatalogoservice.service;

import com.lucca.sitecatalogoservice.dto.SiteRequestDTO;
import com.lucca.sitecatalogoservice.dto.SiteResponseDTO;
import com.lucca.sitecatalogoservice.model.Site;
import com.lucca.sitecatalogoservice.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;

    public SiteResponseDTO inserir(SiteRequestDTO site){
        List<Site> siteList = siteRepository.findByNomeContainingIgnoreCase(site.nome());

        if(!siteList.isEmpty()){
            throw new IllegalArgumentException("site already inserted");
        }

        return toResponseDTO(siteRepository.save(Site.builder().nome(site.nome()).endereco(site.endereco()).build()));
    }

    public SiteResponseDTO alterar(String id, SiteRequestDTO newSite){
        Optional<Site> currentSites = siteRepository.findById(id);

        if(currentSites.isEmpty()){
            throw new IllegalArgumentException("site not found");
        }

        Site targetSite = currentSites.get();

        targetSite.setNome(newSite.nome());
        targetSite.setEndereco(newSite.endereco());

        return toResponseDTO(siteRepository.save(targetSite));
    }

    public List<SiteResponseDTO> listarTodos(){
        return siteRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public List<SiteResponseDTO> localizarPorNome(String nome){
        return siteRepository.findByNomeContainingIgnoreCase(nome).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public void excluir(String id){
        if(!siteRepository.existsById(id)){
            throw new IllegalArgumentException("site not found");
        }
        siteRepository.deleteById(id);
    }

    private SiteResponseDTO toResponseDTO(Site site){
        return new SiteResponseDTO(site.getId(), site.getNome(), site.getEndereco());
    }
}
