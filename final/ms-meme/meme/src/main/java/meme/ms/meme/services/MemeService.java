package meme.ms.meme.services;

import meme.ms.meme.models.MemeModel;
import meme.ms.meme.repositories.MemeRepository;
import org.springframework.stereotype.Service;

@Service
public class MemeService {

	final MemeRepository memeRepository;

	public MemeService(MemeRepository memeRepository) {
		this.memeRepository = memeRepository;
	}

	public MemeModel save(MemeModel memeModel) {
		return memeRepository.save(memeModel);
	}
}
