package com.lab.catalogue.domain;

import com.lab.catalogue.repository.CatalogueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CatalogueServiceTest {

    private CatalogueRepository catalogueRepository = Mockito.mock(CatalogueRepository.class);

    private CatalogueService catalogueService = new CatalogueService(catalogueRepository);

    @Test
    public void save_catalogue_current() {
        String restaurantId = UUID.randomUUID().toString();

        Catalogue newCurrent = new Catalogue();
        newCurrent.setRestaurantId(restaurantId);
        newCurrent.setCurrent(true);

        Catalogue oldCurrent = new Catalogue();
        oldCurrent.setRestaurantId(restaurantId);
        oldCurrent.setCurrent(true);

        Mockito.when(catalogueRepository.findByRestaurantIdAndCurrent(restaurantId, true)).thenReturn(Optional.of(oldCurrent));
        Mockito.when(catalogueRepository.save(newCurrent)).thenReturn(newCurrent);

        Catalogue saved = catalogueService.save(newCurrent);

        assertEquals(saved.getId(), newCurrent.getId());
        assertTrue(saved.isCurrent());
        assertEquals(saved.getRestaurantId(), restaurantId);
    }

    @Test
    public void save_catalogue_not_current() {
        String restaurantId = UUID.randomUUID().toString();

        Catalogue newCurrent = new Catalogue();
        newCurrent.setRestaurantId(restaurantId);
        newCurrent.setCurrent(false);
        Mockito.when(catalogueRepository.save(newCurrent)).thenReturn(newCurrent);

        Catalogue saved = catalogueService.save(newCurrent);

        assertEquals(saved.getId(), newCurrent.getId());
        assertFalse(saved.isCurrent());
        assertEquals(saved.getRestaurantId(), restaurantId);
    }


    @Test
    public void update_catalogue() {
        String restaurantId = UUID.randomUUID().toString();

        Catalogue oldCatalogue = new Catalogue();
        oldCatalogue.setRestaurantId(restaurantId);
        oldCatalogue.setCurrent(false);

        Catalogue catalogueToUpdate = new Catalogue();
        catalogueToUpdate.setId(oldCatalogue.getId());
        catalogueToUpdate.setRestaurantId(restaurantId);
        catalogueToUpdate.setCurrent(true);

        Mockito.when(catalogueRepository.findById(catalogueToUpdate.getId())).thenReturn(Optional.of(oldCatalogue));
        Mockito.when(catalogueRepository.save(catalogueToUpdate)).thenReturn(catalogueToUpdate);

        Catalogue updated = catalogueService.update(catalogueToUpdate);

        assertEquals(updated.getId(), catalogueToUpdate.getId());
        assertTrue(updated.isCurrent());
        assertEquals(updated.getRestaurantId(), restaurantId);
    }

    @Test
    public void current_catalogue() {
        String restaurantId = UUID.randomUUID().toString();

        Catalogue current = new Catalogue();
        current.setRestaurantId(restaurantId);
        current.setCurrent(true);

        Mockito.when(catalogueRepository.findByRestaurantIdAndCurrent(restaurantId, true)).thenReturn(Optional.of(current));

        Optional<Catalogue> currentFound = catalogueService.current(restaurantId);
        assertTrue(currentFound.isPresent());
        assertTrue(currentFound.get().isCurrent());
        assertEquals(currentFound.get().getRestaurantId(), restaurantId);
    }

    @Test
    public void find_catalogue() {
        Catalogue catalogue = new Catalogue();
        catalogue.setCurrent(false);
        Mockito.when(catalogueRepository.findById(catalogue.getId())).thenReturn(Optional.of(catalogue));

        Catalogue catalogueFound = catalogueService.catalogue(catalogue.getId());

        assertEquals(catalogueFound.getId(), catalogue.getId());
        assertFalse(catalogueFound.isCurrent());
    }


    @Test
    public void find_catalogues_by_restaurant_id() {
        String restaurantId = UUID.randomUUID().toString();

        Catalogue catalogue1 = new Catalogue();
        catalogue1.setRestaurantId(restaurantId);
        catalogue1.setCurrent(false);

        Catalogue catalogue2 = new Catalogue();
        catalogue2.setRestaurantId(restaurantId);
        catalogue2.setCurrent(false);

        List<Catalogue> catalogues = Arrays.asList(catalogue1, catalogue2);

        Mockito.when(catalogueRepository.findByRestaurantId(restaurantId)).thenReturn(catalogues);

        List<Catalogue> cataloguesFound = catalogueService.cataloguesByRestaurantId(restaurantId);

        assertNotNull(cataloguesFound);
        assertEquals(cataloguesFound.size(), 2);
    }

    @Test
    public void delete_catalogue() {
        String restaurantId = UUID.randomUUID().toString();

        Catalogue oldCatalogue = new Catalogue();
        oldCatalogue.setRestaurantId(restaurantId);
        oldCatalogue.setCurrent(false);

        Mockito.when(catalogueRepository.findById(oldCatalogue.getId())).thenReturn(Optional.of(oldCatalogue));

        catalogueService.delete(oldCatalogue.getId());

        verify(catalogueRepository, times(1)).deleteById(oldCatalogue.getId());
    }

}