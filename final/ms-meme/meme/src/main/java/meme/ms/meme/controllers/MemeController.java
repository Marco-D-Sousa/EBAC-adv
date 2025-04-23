package meme.ms.meme.controllers;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(MemeController.class);
	private final RestTemplate restTemplate = new RestTemplate();
	private final ObjectMapper objectMapper;

	public MemeController(MemeService memeService, MemeRepository memeRepository, ObjectMapper objectMapper) {
		this.memeService = memeService;
		this.memeRepository = memeRepository;
		this.objectMapper = objectMapper;
	}

	@PostMapping("/memes/{categoryId}")
	public ResponseEntity<MemeModel> saveMeme(@RequestBody @Valid MemeDto memeDto, @PathVariable String categoryId) {

		String categoryUrl = "http://localhost:8083/categories/" + categoryId;// dfc6f4a0-c549-4900-9fc8-9bcb38128325

		try {
			var categoryJson = restTemplate.getForObject(categoryUrl, String.class);

			if (categoryJson == null) {
				log.warn("Categoria {} não encontrada", categoryId);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			JsonNode jsonNode = objectMapper.readTree(categoryJson);
			String categoryIdFromService = jsonNode.get("categoryId").asText();
			UUID categoryUUID = UUID.fromString(categoryIdFromService);

			MemeModel newMeme = new MemeModel();
			BeanUtils.copyProperties(memeDto, newMeme);
			newMeme.setCategoryId(categoryUUID);

			MemeModel savedMeme = memeRepository.save(newMeme);

			log.info("Novo meme salvo: id= {} - data= {}", savedMeme.getMemeId(), savedMeme.getCreated_at());
			return ResponseEntity.status(HttpStatus.CREATED).body(savedMeme);

		} catch (Exception e) {
			log.error("Erro ao criar novo meme: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
