package com.plannr.repository;

/*
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import({PlanMapper.class, UserMapper.class, CommentMapper.class, ApprovalMapper.class})
// Import service and mapper
public class PlanServiceImplTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanServiceImpl planService;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setup() {
        // Create and save a test user
        User creator = new User();
        creator.setUsername("johndoe");
        creator.setEmail("johndoe@example.com");
        creator.setFirstname("John");
        creator.setLastname("Doe");
        creator.setPassword("password123");
        User savedCreator = userRepository.save(creator);

        // Create and save a test plan
        Plan plan = new Plan();
        plan.setTitle("Weekend Getaway");
        plan.setDescription("Plan for a weekend hiking trip.");
        plan.setLocation("Grand Canyon");
        plan.setStartTime(ZonedDateTime.now().plusDays(1));
        plan.setEndTime(ZonedDateTime.now().plusDays(2));
        plan.setCreator(savedCreator);

        // Create and associate approvals
        Approval approval = new Approval();
        approval.setUser(savedCreator);
        approval.setStatus(ApprovalStatusEnum.valueOf("APPROVED"));
        approval.setPlan(plan);

        plan.setApprovals(List.of(approval));

        // Create and associate comments
        Comment comment = new Comment();
        comment.setCommenter(savedCreator);
        comment.setContent("Looking forward to this!");
        comment.setPlan(plan);

        plan.setComments(List.of(comment));

        // Save the plan with associated approvals and comments
        planRepository.save(plan);
    };
/*
    @Test
    void testGetPlanWithApprovalsAndComments() throws JsonProcessingException {
        // Find the saved plan ID from the repository
        Plan savedPlan = planRepository.findAll().get(0);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Act: Call the service method
        PlanDTO result = planService.getPlanWithApprovalsUsersAndComments(savedPlan.getId());
        System.out.println("PlanDTO as JSON: " + objectMapper.writeValueAsString(result));
        // Assert: Verify the returned DTO
        assertNotNull(result, "The result should not be null.");
        assertEquals(savedPlan.getTitle(), result.title(), "The title should match.");
        assertEquals(savedPlan.getDescription(), result.description(), "The description should match.");
        assertEquals(savedPlan.getCreator().getUsername(), result.creator().username(), "The creator's username should match.");

        // Verify approvals
        assertNotNull(result.approvals(), "Approvals should not be null.");
        assertEquals(1, result.approvals().size(), "There should be one approval.");
        assertEquals(ApprovalStatusEnum.valueOf("APPROVED"), result.approvals().get(0).status(), "Approval status should match.");

        // Assert: Verify the returned DTO
        assertNotNull(result, "The result should not be null.");
        assertNotNull(result.creator(), "The creator should not be null.");
        assertEquals(savedPlan.getCreator().getUsername(), result.creator().username(), "The creator's username should match.");

        // Verify comments
        assertNotNull(result.comments(), "Comments should not be null.");
        assertEquals(1, result.comments().size(), "There should be one comment.");
        assertEquals("Looking forward to this!", result.comments().get(0).content(), "Comment text should match.");
    }
}

 */