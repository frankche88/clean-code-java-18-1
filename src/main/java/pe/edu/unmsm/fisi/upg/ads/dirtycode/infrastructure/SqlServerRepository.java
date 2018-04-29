package pe.edu.unmsm.fisi.upg.ads.dirtycode.infrastructure;

import pe.edu.unmsm.fisi.upg.ads.dirtycode.domain.IRepository;
import pe.edu.unmsm.fisi.upg.ads.dirtycode.domain.Speaker;

public class SqlServerRepository implements IRepository {
	public int saveSpeaker(Speaker speaker) {
		// TODO: Save speaker to SQL Server DB. For now, just assume success and return 1.
		return 1;
	}

	public int getRegistrationFee(int experience) {
		
	
		
		if (experience <= 1) {
			return 500;
		}
		
		if (experience >= 2 && experience <= 3) {
			return 250;
		}
		
		if (experience >= 4 && experience <= 5) {
			return 100;
		}
		
		if (experience >= 6 && experience <= 9) {
			return 50;
		}
		
		return 0;
	}
}