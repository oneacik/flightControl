package com.ksidelta.pg.model.contexts.flight.id;

import com.ksidelta.pg.model.kernel.flight.FlightId;
import com.ksidelta.pg.model.kernel.flight.InvalidFlightIdException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightIdTest {
    @ParameterizedTest
    @ValueSource(strings = {"KLM12345BSA", "AAA09876BBB"})
    public void givenValidFlightIdWhenFlightIdIsCreatedThenCreationSucceeds(String flightId) {
        final var flightIdObject = FlightId.createFlightId(flightId);

        assertThat(flightIdObject.getFlightId(), equalTo(flightId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"LM12345BSA","KLM1345BSA","KLM12345BA","KLM123A5BSA", "KLM12345BS4", "K7M12345BSA", })
    public void givenInvalidFlightIdWhenFlightIdIsCreatedThenInvalidFlightIdIsThrown(String flightId) {
        assertThrows(InvalidFlightIdException.class, () -> FlightId.createFlightId(flightId));
    }

}
