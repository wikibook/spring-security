package com.laurentiuspilca.ssia;

import com.laurentiuspilca.ssia.entities.Workout;
import com.laurentiuspilca.ssia.repositories.WorkoutRepository;
import com.laurentiuspilca.ssia.service.WorkoutService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@EnableAutoConfiguration(
        exclude = {DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class})
class MethodTests {

    @Autowired
    private WorkoutService workoutService;

    @MockBean
    private WorkoutRepository workoutRepository;

    @Test
    @DisplayName("When the method is called without a user, " +
            "it throws AuthenticationException")
    void testProductServiceWithNoUser() {
        Workout w = new Workout();
        assertThrows(AuthenticationException.class,
                () -> workoutService.saveWorkout(w));
    }

    @Test
    @DisplayName("When saving a workout, " +
            "the app accepts the method call " +
            "only if the workout belongs to the authenticated user.")
    @WithMockUser(username = "bill")
    void testSaveWorkoutWorksOnlyForAuthenticatedUser() {
        Workout w = new Workout();
        w.setUser("bill");
        workoutService.saveWorkout(w);
    }

}
