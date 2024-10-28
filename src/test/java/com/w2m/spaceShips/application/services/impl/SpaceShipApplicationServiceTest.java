package com.w2m.spaceShips.application.services.impl;

import com.w2m.spaceShips.application.exception.AlreadyExistException;
import com.w2m.spaceShips.application.exception.BusinessRuleViolatedException;
import com.w2m.spaceShips.application.exception.NotFoundException;
import com.w2m.spaceShips.application.factories.SpaceShipFactory;
import com.w2m.spaceShips.application.models.SpaceShipDTO;
import com.w2m.spaceShips.application.services.impl.impl.SpaceShipApplicationServiceImpl;
import com.w2m.spaceShips.domain.enums.Equipment;
import com.w2m.spaceShips.domain.models.SpaceShipDomain;
import com.w2m.spaceShips.domain.models.SpaceShipEquipmentDomain;
import com.w2m.spaceShips.domain.ports.in.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author javiloguai
 */
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration
public class SpaceShipApplicationServiceTest {
    private static final String ID_MANDATORY = "Id field is Mandatory";

    private static final String NAME_EMPTY = "SpaceShip's name cannot be empty";

    private static final String EQUIPMENT_EMPTY = "SpaceShip's equipment list cannot be empty";

    private static final String PAGE_MANDATORY = "page info is Mandatory";

    private static final String NAME_MANDATORY = "name field is Mandatory";

    private static final String EQUIPMENT_MANDATORY = "equipment field is Mandatory";

    private static final String SPACE_SHIP_MANDATORY = "The SpaceShip Object is Mandatory";

    //@InjectMocks
    private SpaceShipApplicationServiceImpl spaceShipApplicationService;

    @Mock
    private GetSpaceShipsUseCase getSpaceShipsUseCase;

    @Mock
    private GetShipEquipmentUseCase getShipEquipmentUseCase;

    @Mock
    private CreateSpaceShipUseCase createSpaceShipUseCase;

    @Mock
    private CreateShipEquipmentUseCase createShipEquipmentUseCase;

    @Mock
    private UpdateSpaceShipUseCase updateSpaceShipUseCase;

    @Mock
    private DeleteSpaceShipUseCase deleteSpaceShipUseCase;

    @Mock
    private DeleteShipEquipmentUseCase deleteShipEquipmentUseCase;

    @Captor
    private ArgumentCaptor<SpaceShipEquipmentDomain> spaceShipEquipmentDomainArgumentCaptor;

    @Captor
    private ArgumentCaptor<SpaceShipDomain> spaceShipDomainArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.spaceShipApplicationService = new SpaceShipApplicationServiceImpl(getSpaceShipsUseCase,
                getShipEquipmentUseCase, createSpaceShipUseCase, createShipEquipmentUseCase, updateSpaceShipUseCase,
                deleteSpaceShipUseCase, deleteShipEquipmentUseCase);
    }

    /**
     * Tests for getAllSpaceShips method
     */
    @Nested
    class getAllSpaceShipsTest {

        @Test
        void givenNullPage_thenThrowException() {

            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.pageAllSpaceShips(null));
            Assertions.assertEquals(PAGE_MANDATORY, ex.getMessage());

        }

        @Test
        void givenNonExistingSpaceShips_thenReturnEmptyPage() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final List<SpaceShipDomain> shipList = List.of();

            final Pageable pageable = PageRequest.of(0, 20);
            final Page<SpaceShipDomain> pageResult = new PageImpl<>(shipList, pageable, 1);
            Mockito.when(getSpaceShipsUseCase.pageAllSpaceShips(any(Pageable.class))).thenReturn(pageResult);

            // when
            final Page<SpaceShipDomain> pageResultDomain = spaceShipApplicationService.pageAllSpaceShips(pageable);

            // then
            Assertions.assertNotNull(pageResultDomain);
            Assertions.assertTrue(pageResultDomain.isEmpty());
            Assertions.assertEquals(shipList.size(), pageResultDomain.getContent().size());

        }

        @Test
        void givenNonExistingSpaceShips_thenReturnEmptyList() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final List<SpaceShipDomain> shipList = List.of();

            Mockito.when(getSpaceShipsUseCase.getAllSpaceShips()).thenReturn(shipList);

            // when
            final List<SpaceShipDomain> listResultDomain = spaceShipApplicationService.getAllSpaceShips();

            // then
            Assertions.assertNotNull(listResultDomain);
            Assertions.assertTrue(listResultDomain.isEmpty());
            Assertions.assertEquals(shipList.size(), listResultDomain.size());
        }

        @Test
        void givenExistingSpaceShips_thenReturnAllPagedSpaceShips() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final SpaceShipDomain chip1 = SpaceShipFactory.getDO(1L);
            final SpaceShipDomain chip2 = SpaceShipFactory.getDO(2L);

            final List<SpaceShipDomain> shipList = List.of(chip1, chip2);

            final Pageable pageable = PageRequest.of(0, 20);
            final Page<SpaceShipDomain> pageResult = new PageImpl<>(shipList, pageable, 1);
            Mockito.when(getSpaceShipsUseCase.pageAllSpaceShips(any(Pageable.class))).thenReturn(pageResult);

            // when
            final Page<SpaceShipDomain> pageResultDomain = spaceShipApplicationService.pageAllSpaceShips(pageable);

            // then
            Assertions.assertEquals(shipList.size(), pageResultDomain.getTotalElements());
            Assertions.assertEquals(shipList.get(0).getId(), pageResultDomain.toList().get(0).getId());
            Assertions.assertEquals(shipList.get(1).getId(), pageResultDomain.toList().get(1).getId());
        }

        @Test
        void givenExistingSpaceShips_thenReturnAllSpaceShips() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final SpaceShipDomain chip1 = SpaceShipFactory.getDO(1L);
            final SpaceShipDomain chip2 = SpaceShipFactory.getDO(2L);

            final List<SpaceShipDomain> shipList = List.of(chip1, chip2);

            Mockito.when(getSpaceShipsUseCase.getAllSpaceShips()).thenReturn(shipList);

            // when
            final List<SpaceShipDomain> listResultDomain = spaceShipApplicationService.getAllSpaceShips();

            // then
            Assertions.assertEquals(shipList.size(), listResultDomain.size());
            Assertions.assertEquals(shipList.get(0).getId(), listResultDomain.get(0).getId());
            Assertions.assertEquals(shipList.get(1).getId(), listResultDomain.get(1).getId());
        }

    }

    /**
     * Tests for getAllSpaceShipsByName method
     */
    @Nested
    class getAllSpaceShipsByNameTest {

        @Test
        void givenNullName_thenThrowException() {

            final Pageable pageable = PageRequest.of(0, 20);

            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.pageAllSpaceShipsByName(null, pageable));
            Assertions.assertEquals(NAME_MANDATORY, ex.getMessage());

        }

        @Test
        void givenNullPage_thenThrowException() {

            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.pageAllSpaceShipsByName("name", null));
            Assertions.assertEquals(PAGE_MANDATORY, ex.getMessage());

        }

        @Test
        void givenNonMatchingSpaceShips_thenReturnEmptyPage() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final List<SpaceShipDomain> shipList = List.of();

            final Pageable pageable = PageRequest.of(0, 20);
            final Page<SpaceShipDomain> pageResult = new PageImpl<>(shipList, pageable, 1);
            Mockito.when(getSpaceShipsUseCase.pageAllSpaceShipsByName(ArgumentMatchers.any(String.class),
                    ArgumentMatchers.any(Pageable.class))).thenReturn(pageResult);

            // when
            final Page<SpaceShipDomain> pageResultDomain = spaceShipApplicationService.pageAllSpaceShipsByName("XXX",
                    pageable);

            // then
            Assertions.assertNotNull(pageResultDomain);
            Assertions.assertTrue(pageResultDomain.isEmpty());
            Assertions.assertEquals(shipList.size(), pageResultDomain.getContent().size());

        }

        @Test
        void givenNonMatchingSpaceShips_thenReturnEmptyList() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final List<SpaceShipDomain> shipList = List.of();
            final Pageable pageable = PageRequest.of(0, 20);
            final Page<SpaceShipDomain> pageResult = new PageImpl<>(shipList, pageable, 1);

            Mockito.when(getSpaceShipsUseCase.pageAllSpaceShipsByName(ArgumentMatchers.any(String.class), ArgumentMatchers.any(Pageable.class)))
                    .thenReturn(pageResult);

            // when
            final Page<SpaceShipDomain> pageResultDomain = spaceShipApplicationService.pageAllSpaceShipsByName("XXX", pageable);

            // then
            Assertions.assertNotNull(pageResultDomain);
            Assertions.assertTrue(pageResultDomain.isEmpty());
            Assertions.assertEquals(shipList.size(), pageResultDomain.getContent().size());
        }

        @Test
        void givenMatchingSpaceShips_thenReturnAllMatchingPagedSpaceShips() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final SpaceShipDomain chip1 = SpaceShipFactory.getDO(1L);
            final SpaceShipDomain chip2 = SpaceShipFactory.getDO(2L);

            final List<SpaceShipDomain> hlist = List.of(chip1, chip2);

            final Pageable pageable = PageRequest.of(0, 20);
            final Page<SpaceShipDomain> pageResult = new PageImpl<>(hlist, pageable, 1);
            Mockito.when(getSpaceShipsUseCase.pageAllSpaceShipsByName(chip1.getName(), pageable)).thenReturn(pageResult);

            // when
            final Page<SpaceShipDomain> pageResultDomain = spaceShipApplicationService.pageAllSpaceShipsByName(
                    chip1.getName(), pageable);

            // then
            Assertions.assertEquals(hlist.size(), pageResultDomain.getTotalElements());
            Assertions.assertEquals(hlist.get(0).getId(), pageResultDomain.toList().get(0).getId());
            Assertions.assertEquals(hlist.get(1).getId(), pageResultDomain.toList().get(1).getId());
        }

        @Test
        void givenMatchingSpaceShips_thenReturnMatchingSpaceShips() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final SpaceShipDomain chip1 = SpaceShipFactory.getDO(1L);
            final SpaceShipDomain chip2 = SpaceShipFactory.getDO(2L);

            final List<SpaceShipDomain> shipList = List.of(chip1, chip2);
            final Pageable pageable = PageRequest.of(0, 20);
            final Page<SpaceShipDomain> pageResult = new PageImpl<>(shipList, pageable, 1);

            Mockito.when(getSpaceShipsUseCase.pageAllSpaceShipsByName(chip1.getName(), pageable)).thenReturn(pageResult);

            // when
            final Page<SpaceShipDomain> pageResultDomain = spaceShipApplicationService.pageAllSpaceShipsByName(
                    chip1.getName(), pageable);

            // then
            Assertions.assertEquals(shipList.size(), pageResultDomain.getTotalElements());
            Assertions.assertEquals(shipList.get(0).getId(), pageResultDomain.toList().get(0).getId());
            Assertions.assertEquals(shipList.get(1).getId(), pageResultDomain.toList().get(1).getId());
        }

    }

    /**
     * Tests for getAllSpaceShipsByEquipment method
     */
    @Nested
    class getAllSpaceShipsByEquipmentTest {

        @Test
        void givenNullShipEquipment_thenThrowException() {

            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.getAllSpaceShipsByEquipment(null));
            Assertions.assertEquals(EQUIPMENT_MANDATORY, ex.getMessage());

        }

        @Test
        void givenNonMatchingShipEquipment_thenReturnEmptyList() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final List<SpaceShipEquipmentDomain> plist = List.of();
            final List<SpaceShipDomain> shipList = List.of();

            List<Long> shipsIds = plist.stream().map(p -> p.getSpaceShipId()).distinct().toList();

            Mockito.when(getShipEquipmentUseCase.findAllEquipmentEquipment(ArgumentMatchers.any(Equipment.class)))
                    .thenReturn(plist);

            Mockito.when(getSpaceShipsUseCase.findSpaceShipsByIdList(shipsIds)).thenReturn(shipList);

            // when
            final List<SpaceShipDomain> listResultDomain = spaceShipApplicationService.getAllSpaceShipsByEquipment(
                    Equipment.LASER_BLASTER);

            // then
            Assertions.assertNotNull(listResultDomain);
            Assertions.assertTrue(listResultDomain.isEmpty());
            Assertions.assertEquals(shipList.size(), listResultDomain.size());
        }

        @Test
        void givenMatchingShipEquipment_thenReturnMatchingSpaceShips() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));

            final SpaceShipDomain h1 = SpaceShipFactory.getDO(1L);
            final SpaceShipDomain h2 = SpaceShipFactory.getDO(2L);

            Equipment equipment = SpaceShipFactory.getSpaceShipEquipment(1L).getShipEquipment();

            SpaceShipEquipmentDomain p1 = SpaceShipFactory.getSpaceShipEquipmentDO(1L, SpaceShipFactory.EQUPMENT_ID);
            SpaceShipEquipmentDomain p2 = SpaceShipFactory.getSpaceShipEquipmentDO(2L, SpaceShipFactory.EQUPMENT_ID + 1);

            final List<SpaceShipEquipmentDomain> plist = List.of(p1, p2);
            final List<SpaceShipDomain> hlist = List.of(h1, h2);

            List<Long> shipsIds = plist.stream().map(p -> p.getSpaceShipId()).distinct().toList();

            Mockito.when(getShipEquipmentUseCase.findAllEquipmentEquipment(equipment)).thenReturn(plist);

            Mockito.when(getSpaceShipsUseCase.findSpaceShipsByIdList(shipsIds)).thenReturn(hlist);

            // when
            final List<SpaceShipDomain> listResultDomain = spaceShipApplicationService.getAllSpaceShipsByEquipment(
                    equipment);

            // then
            Assertions.assertNotNull(listResultDomain);
            Assertions.assertEquals(hlist.size(), listResultDomain.size());
            Assertions.assertEquals(hlist.get(0).getId(), listResultDomain.get(0).getId());
            Assertions.assertEquals(hlist.get(1).getId(), listResultDomain.get(1).getId());
            Assertions.assertEquals(listResultDomain.get(0).getEquipment().get(0).getShipEquipment(), equipment);
            Assertions.assertEquals(listResultDomain.get(1).getEquipment().get(0).getShipEquipment(), equipment);
        }

    }

    /**
     * Tests for findById method
     */

    @Nested
    class findByIdTest {
        @Test
        void givenNullId_thenThrowException() {
            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.findSpaceShipById(null));
            Assertions.assertEquals(ID_MANDATORY, ex.getMessage());
        }

        @Test
        void givenNotMatchingId_thenThrowNotFoundException() {
            Mockito.when(getSpaceShipsUseCase.findSpaceShipById(ArgumentMatchers.anyLong()))
                    .thenReturn(Optional.empty());
            final NotFoundException ex = Assertions.assertThrows(NotFoundException.class,
                    () -> spaceShipApplicationService.findSpaceShipById(SpaceShipFactory.SPACE_SHIP_ID));
            Assertions.assertEquals("Not found SpaceShip with id " + SpaceShipFactory.SPACE_SHIP_ID, ex.getMessage());
        }

        @Test
        void givenMatchingId_thenReturnsTheSpaceShip() {
            // given
            //MockSecurity.setMockUserInTest(MockSecurity.getUser(Role.USER));
            final SpaceShipDomain chip1 = SpaceShipFactory.getDO();
            Mockito.when(getSpaceShipsUseCase.findSpaceShipById(ArgumentMatchers.anyLong()))
                    .thenReturn(Optional.of(chip1));
            // when
            final SpaceShipDomain resultDomain = spaceShipApplicationService.findSpaceShipById(
                    SpaceShipFactory.SPACE_SHIP_ID);
            // then
            Assertions.assertNotNull(resultDomain);
            Assertions.assertEquals(chip1.getId(), resultDomain.getId());
            Assertions.assertEquals(chip1.getName(), resultDomain.getName());
            Assertions.assertEquals(chip1.getEquipment().size(), resultDomain.getEquipment().size());
            Assertions.assertEquals(chip1.getEquipment().get(0).getShipEquipment(),
                    resultDomain.getEquipment().get(0).getShipEquipment());
        }
    }

    @Nested
    class addEquipmentTest {

        @Test
        void givenNullParameters_ThenThrowsException() {
            //given

            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.addEquipment(null, Equipment.INVULNERABILITY_SHIELD));
            Assertions.assertEquals(ID_MANDATORY, ex.getMessage());

            final BusinessRuleViolatedException ex2 = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.addEquipment(SpaceShipFactory.SPACE_SHIP_ID, null));
            Assertions.assertEquals(EQUIPMENT_MANDATORY, ex2.getMessage());

        }

        @Test
        void givenNonExistingSpaceShip_ThenThrowsNotFoundException() {
            //given

            Mockito.when(getSpaceShipsUseCase.findSpaceShipById(ArgumentMatchers.anyLong()))
                    .thenReturn(Optional.empty());

            final NotFoundException ex = Assertions.assertThrows(NotFoundException.class,
                    () -> spaceShipApplicationService.addEquipment(SpaceShipFactory.SPACE_SHIP_ID,
                            Equipment.INVULNERABILITY_SHIELD));
            Assertions.assertEquals("Not found SpaceShip with id " + SpaceShipFactory.SPACE_SHIP_ID, ex.getMessage());

        }

        @Test
        void givenExistingSpaceShip_WhenAlreadyHasThatShipEquipment_ThenThrowsAlreadyExistException() {
            //given

            final SpaceShipDomain chip1 = SpaceShipFactory.getDO();

            Mockito.when(getSpaceShipsUseCase.findSpaceShipById(ArgumentMatchers.anyLong()))
                    .thenReturn(Optional.of(chip1));

            final AlreadyExistException ex = Assertions.assertThrows(AlreadyExistException.class,
                    () -> spaceShipApplicationService.addEquipment(SpaceShipFactory.SPACE_SHIP_ID,
                            Equipment.INVULNERABILITY_SHIELD));
            Assertions.assertEquals("This SpaceShip already has that ship equipment: " + Equipment.INVULNERABILITY_SHIELD,
                    ex.getMessage());

        }

        @Test
        void givenExistingSpaceShip_WhenDoesNotHaveThatShipEquipment_ThenAddShipEquipment() {
            //given
            final SpaceShipDomain chip1 = SpaceShipFactory.getDO();

            Mockito.when(getSpaceShipsUseCase.findSpaceShipById(ArgumentMatchers.anyLong()))
                    .thenReturn(Optional.of(chip1));

            //when
            SpaceShipDomain result = spaceShipApplicationService.addEquipment(SpaceShipFactory.SPACE_SHIP_ID,
                    Equipment.EMP_ELECTROMAGNETIC_PULSE_GENERATOR);

            //then
            Mockito.verify(createShipEquipmentUseCase, Mockito.times(1))
                    .addShipEquipment(spaceShipEquipmentDomainArgumentCaptor.capture());
            Assertions.assertEquals(Equipment.EMP_ELECTROMAGNETIC_PULSE_GENERATOR,
                    spaceShipEquipmentDomainArgumentCaptor.getValue().getShipEquipment());
            Assertions.assertNotNull(result);
            List<Equipment> equipList = result.getEquipment().stream().map(p -> p.getShipEquipment()).distinct()
                    .toList();
            //TODO
            // Has to Mock spaceShipRepository.findById the second time to return an updated spaceShip
            //Assertions.assertTrue(equipList.contains(Equipment.EMP_ELECTROMAGNETIC_PULSE_GENERATOR));
        }

    }

    @Nested
    class createSpaceShipTest {

        @Test
        void givenNullParameters_ThenThrowsException() {
            //given
            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.createSpaceShip(null));
            Assertions.assertEquals(SPACE_SHIP_MANDATORY, ex.getMessage());

        }

        @Test
        void givenNotNullDto_WhenDtoIsNotValid_ThenThrowsException() {
            //given
            SpaceShipDTO noName = SpaceShipFactory.getDTO();
            noName.setName(null);
            SpaceShipDTO noShipEquipment = SpaceShipFactory.getDTO();
            noShipEquipment.setEquipment(null);
            SpaceShipDTO alreadyExisting = SpaceShipFactory.getDTO();
            SpaceShipDomain alreadyExistingD = SpaceShipFactory.getDO();
            alreadyExisting.setName("alreadyExisting");
            alreadyExistingD.setName("alreadyExisting");

            Mockito.when(getSpaceShipsUseCase.findFirstByName("alreadyExisting"))
                    .thenReturn(Optional.of(alreadyExistingD));
            //    Mockito.when(getSpaceShipsUseCase.findFirstByName(noShipEquipment.getName())).thenReturn(Optional.empty());

            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.createSpaceShip(noName));
            Assertions.assertEquals(NAME_EMPTY, ex.getMessage());

            final BusinessRuleViolatedException ex2 = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.createSpaceShip(noShipEquipment));
            Assertions.assertEquals(EQUIPMENT_EMPTY, ex2.getMessage());

            final AlreadyExistException ex3 = Assertions.assertThrows(AlreadyExistException.class,
                    () -> spaceShipApplicationService.createSpaceShip(alreadyExisting));
            Assertions.assertTrue(ex3.getMessage().contains("This SpaceShip already exists"));

        }

        @Test
        void givenValidatedDto_ThenCreateSpaceShip() {
            //given

            SpaceShipDTO validDto = SpaceShipFactory.getDTO();
            SpaceShipDomain validDomain = SpaceShipFactory.getDO();

            Mockito.when(getSpaceShipsUseCase.findFirstByName(ArgumentMatchers.anyString()))
                    .thenReturn(Optional.empty());
            Mockito.when(getSpaceShipsUseCase.findSpaceShipById(ArgumentMatchers.anyLong()))
                    .thenReturn(Optional.of(validDomain));

            Mockito.when(createSpaceShipUseCase.createSpaceShip(validDomain)).thenReturn(validDomain);

            //when
            spaceShipApplicationService.createSpaceShip(validDto);

            //then
            Mockito.verify(createSpaceShipUseCase, Mockito.times(1))
                    .createSpaceShip(spaceShipDomainArgumentCaptor.capture());
            Mockito.verify(createShipEquipmentUseCase, Mockito.times(1)).assignAllEquipment(ArgumentMatchers.any());

            Assertions.assertEquals(validDto.getId(), spaceShipDomainArgumentCaptor.getValue().getId());
            Assertions.assertEquals(validDto.getName(), spaceShipDomainArgumentCaptor.getValue().getName());
            Assertions.assertEquals(validDto.getMediaShow(),
                    spaceShipDomainArgumentCaptor.getValue().getMediaShow());
        }

    }

    @Nested
    class updateSpaceShipTest {

        @Test
        void givenNullParameters_ThenThrowsException() {
            //given
            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.updateSpaceShip(null, SpaceShipFactory.getDTO()));
            Assertions.assertEquals(ID_MANDATORY, ex.getMessage());

            final BusinessRuleViolatedException ex2 = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.updateSpaceShip(SpaceShipFactory.SPACE_SHIP_ID, null));
            Assertions.assertEquals(SPACE_SHIP_MANDATORY, ex2.getMessage());

        }

        @Test
        void givenNotNullDto_WhenDtoIsNotValid_ThenThrowsException() {
            //given
            SpaceShipDTO noName = SpaceShipFactory.getDTO();
            noName.setName(null);
            SpaceShipDTO noShipEquipment = SpaceShipFactory.getDTO();
            noShipEquipment.setEquipment(null);
            SpaceShipDTO alreadyExisting = SpaceShipFactory.getDTO();
            SpaceShipDomain alreadyExistingD = SpaceShipFactory.getDO(288L);
            alreadyExisting.setName("alreadyExisting");
            alreadyExistingD.setName("alreadyExisting");

            Mockito.when(getSpaceShipsUseCase.findFirstByName("alreadyExisting"))
                    .thenReturn(Optional.of(alreadyExistingD));

            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.updateSpaceShip(null, SpaceShipFactory.getDTO()));
            Assertions.assertEquals(ID_MANDATORY, ex.getMessage());

            final BusinessRuleViolatedException ex2 = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.updateSpaceShip(SpaceShipFactory.SPACE_SHIP_ID, noName));
            Assertions.assertEquals(NAME_EMPTY, ex2.getMessage());

            final BusinessRuleViolatedException ex3 = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.updateSpaceShip(SpaceShipFactory.SPACE_SHIP_ID, noShipEquipment));
            Assertions.assertEquals(EQUIPMENT_EMPTY, ex3.getMessage());

            final AlreadyExistException ex4 = Assertions.assertThrows(AlreadyExistException.class,
                    () -> spaceShipApplicationService.updateSpaceShip(SpaceShipFactory.SPACE_SHIP_ID, alreadyExisting));
            Assertions.assertTrue(ex4.getMessage().contains("This SpaceShip already exists"));

        }

        @Test
        void givenValidatedDto_ThenUpdatesSpaceShip() {
            //given

            SpaceShipDTO validDto = SpaceShipFactory.getDTO();
            SpaceShipDomain validDomain = SpaceShipFactory.getDO();

            Mockito.when(getSpaceShipsUseCase.findFirstByName(ArgumentMatchers.anyString()))
                    .thenReturn(Optional.empty());
            Mockito.when(getSpaceShipsUseCase.findSpaceShipById(ArgumentMatchers.anyLong()))
                    .thenReturn(Optional.of(validDomain));
            //TODO Must fix inmutable collection when Mock
            //when
            spaceShipApplicationService.updateSpaceShip(SpaceShipFactory.SPACE_SHIP_ID, validDto);

            //then
            Mockito.verify(updateSpaceShipUseCase, Mockito.times(1))
                    .updateSpaceShip(spaceShipDomainArgumentCaptor.capture());
            Mockito.verify(createShipEquipmentUseCase, Mockito.times(1)).assignAllEquipment(ArgumentMatchers.any());

            Assertions.assertEquals(validDto.getId(), spaceShipDomainArgumentCaptor.getValue().getId());
            Assertions.assertEquals(validDto.getName(), spaceShipDomainArgumentCaptor.getValue().getName());
            Assertions.assertEquals(validDto.getMediaShow(),
                    spaceShipDomainArgumentCaptor.getValue().getMediaShow());
        }

    }

    /**
     * deleteSpaceShipById test cases
     */
    @Nested
    @DisplayName("deleteSpaceShipById test cases")
    class deleteSpaceShipByIdTest {
        @Test
        @DisplayName("Throws exception when spaceShip ID is null")
        void givenNullId_thenThrowsException() {
            final BusinessRuleViolatedException ex = Assertions.assertThrows(BusinessRuleViolatedException.class,
                    () -> spaceShipApplicationService.deleteSpaceShipById(null));
            Assertions.assertEquals(ID_MANDATORY, ex.getMessage());
        }

        @Test
        @DisplayName("Throws exception when spaceShip ID does not exist")
        void givenNonExistingId_thenThrowsException() {

            Mockito.when(getSpaceShipsUseCase.findSpaceShipById(SpaceShipFactory.SPACE_SHIP_ID))
                    .thenReturn(Optional.empty());

            final NotFoundException ex = Assertions.assertThrows(NotFoundException.class,
                    () -> spaceShipApplicationService.deleteSpaceShipById(SpaceShipFactory.SPACE_SHIP_ID));
            Assertions.assertEquals("Not found SpaceShip with id " + SpaceShipFactory.SPACE_SHIP_ID, ex.getMessage());
        }

        @Test
        void givenExistingSpaceShip_thenDeletesIt() {

            final SpaceShipDomain spaceShipToDelete = SpaceShipFactory.getDO();

            Mockito.when(getSpaceShipsUseCase.findSpaceShipById(SpaceShipFactory.SPACE_SHIP_ID))
                    .thenReturn(Optional.of(spaceShipToDelete));

            spaceShipApplicationService.deleteSpaceShipById(SpaceShipFactory.SPACE_SHIP_ID);

            Mockito.verify(deleteShipEquipmentUseCase, Mockito.times(1)).deleteAllBySpaceShipId(SpaceShipFactory.SPACE_SHIP_ID);
            Mockito.verify(deleteSpaceShipUseCase, Mockito.times(1)).deleteSpaceShipById(SpaceShipFactory.SPACE_SHIP_ID);

        }

    }

    /**
     * deleteAllSpaceShips test cases
     */
    @Nested
    @DisplayName("deleteAllSpaceShips test cases")
    class deleteAllSpaceShips {
        @Test
        @DisplayName("Does nothing when no existing registers")
        void givenNonExistingRegisters_thenDoNothing() {

//            Mockito.when(getShipEquipmentUseCase.findAllEquipment()).thenReturn(List.of());
//            Mockito.when(getSpaceShipsUseCase.getAllSpaceShips()).thenReturn(List.of());

            spaceShipApplicationService.deleteAllSpaceShips();

            Mockito.verify(deleteShipEquipmentUseCase, Mockito.times(0)).deleteAll();
            Mockito.verify(deleteSpaceShipUseCase, Mockito.times(0)).deleteAllSpaceShips();
        }

        @Test
        @DisplayName("Deletes all when existing registers")
        void givenExistingRegisters_thenDeletesAll() {

            final SpaceShipDomain spaceShipToDelete = SpaceShipFactory.getDO();
            final SpaceShipEquipmentDomain equipmentToDelete = SpaceShipFactory.getSpaceShipEquipmentDO(SpaceShipFactory.SPACE_SHIP_ID);

            Mockito.when(getShipEquipmentUseCase.findAllEquipment()).thenReturn(List.of(equipmentToDelete));
            Mockito.when(getSpaceShipsUseCase.getAllSpaceShips()).thenReturn(List.of(spaceShipToDelete));

            spaceShipApplicationService.deleteAllSpaceShips();

            Mockito.verify(deleteShipEquipmentUseCase, Mockito.times(1)).deleteAll();
            Mockito.verify(deleteSpaceShipUseCase, Mockito.times(1)).deleteAllSpaceShips();

        }

    }

}
