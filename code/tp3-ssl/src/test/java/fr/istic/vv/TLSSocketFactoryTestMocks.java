package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols() {
        // Arrange
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        // Act & Assert
        f.prepareSocket(mockSocket);

        // Verify that setEnabledProtocols is never called
        verify(mockSocket, never()).setEnabledProtocols(Mockito.any(String[].class));
    }

    @Test
    public void typical() {
        // Arrange
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols())
                .thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSocket.getEnabledProtocols())
                .thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        // Act
        f.prepareSocket(mockSocket);

        // Assert
        verify(mockSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = Arrays.asList(in);
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}
