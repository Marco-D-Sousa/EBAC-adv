package meme.ms.meme.controllers;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import meme.ms.meme.dtos.MemeDto;
import meme.ms.meme.models.MemeModel;
import meme.ms.meme.repositories.MemeRepository;
import meme.ms.meme.services.MemeService;

@RestController
public class MemeController {

	final MemeService memeService;
	final MemeRepository memeRepository;

	public MemeController(MemeService memeService, MemeRepository memeRepository) {
		this.memeService = memeService;
		this.memeRepository = memeRepository;
	}

	@PostMapping("/memes/{categoryId}")
	public ResponseEntity<MemeModel> saveMeme(@RequestBody @Valid MemeDto memeDto, @PathVariable String categoryId) {

		RestTemplate restTemplate = new RestTemplate();
		String categoryUrl = "http://localhost:8083/categories/" + categoryId;// dfc6f4a0-c549-4900-9fc8-9bcb38128325

		var categoryJson = restTemplate.getForObject(categoryUrl, String.class);

		if (categoryJson != null) {
			
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(categoryJson);
				String categoryIdString = jsonNode.get("categoryId").asText();
				UUID categoryIdUUID = UUID.fromString(categoryIdString);

				MemeModel newMeme = new MemeModel();
				BeanUtils.copyProperties(memeDto, newMeme);
				newMeme.setCategoryId(categoryIdUUID);

				System.out.println("Categoria encontrada: " + categoryJson);

				return ResponseEntity.status(HttpStatus.CREATED).body(memeRepository.save(newMeme));
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("Erro ao processar a categoria: " + e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} else {
			System.out.println("Categoria não encontrada");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@GetMapping("/memes")
	public ResponseEntity<List<MemeModel>> allMemes() {

		List<MemeModel> memes = memeRepository.findAll();

		if (!memes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(memes);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("memes/random")
	public ResponseEntity<MemeModel> memeOfDay() {

		List<MemeModel> memes = memeRepository.findAll();

		var random = new Random();
		var sortedNumber = random.nextInt(memes.size());

		var memeSortiado = memes.get(sortedNumber);
		return ResponseEntity.status(HttpStatus.OK).body(memeSortiado);
	}
}
