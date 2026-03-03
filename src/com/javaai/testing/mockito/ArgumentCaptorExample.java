package com.javaai.testing.mockito;

/**
 * ArgumentCaptorExample
 *
 * Objetivo:
 *  - Mostrar como capturar argumentos passados a um mock usando ArgumentCaptor.
 */
public class ArgumentCaptorExample {

    public static void main(String[] args) {
        System.out.println("=== Mockito – ArgumentCaptor ===");
        System.out.println("Use ArgumentCaptor para inspecionar os valores enviados a um mock.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5 + Mockito)     ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import org.mockito.ArgumentCaptor;
    // import static org.mockito.Mockito.*;
    // import static org.junit.jupiter.api.Assertions.*;
    //
    // class ArgumentCaptorExampleTest {
    //
    //     interface NotificationService {
    //         void notifyMember(String memberEmail, String message);
    //     }
    //
    //     static class LibraryNotifier {
    //         private final NotificationService service;
    //
    //         LibraryNotifier(NotificationService service) {
    //             this.service = service;
    //         }
    //
    //         void notifyOverdue(String email) {
    //             service.notifyMember(email, "Você possui livros em atraso.");
    //         }
    //     }
    //
    //     @Test
    //     void deveCapturarArgumentosEnviadosAoMock() {
    //         NotificationService service = mock(NotificationService.class);
    //         LibraryNotifier notifier = new LibraryNotifier(service);
    //
    //         notifier.notifyOverdue("user@example.com");
    //
    //         ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
    //         ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
    //
    //         verify(service).notifyMember(emailCaptor.capture(), messageCaptor.capture());
    //
    //         assertEquals("user@example.com", emailCaptor.getValue());
    //         assertTrue(messageCaptor.getValue().contains("atraso"));
    //     }
    // }
}

