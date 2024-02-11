# BudgetCalculator - Aplicación de Presupuestos para Empresas Contratistas

## Descripción

BudgetCalculator es una aplicación Java diseñada para facilitar la creación y seguimiento de presupuestos en empresas contratistas que se dedican a proyectos de construcción, reparaciones y acabados. La aplicación proporciona una interfaz intuitiva y herramientas que permiten a los usuarios gestionar eficientemente los costos asociados con sus proyectos.

## Características clave

- **Creación de Presupuestos**: Permite a los usuarios crear fácilmente presupuestos detallados para proyectos específicos.
- **Seguimiento de Costos**: Facilita el seguimiento en tiempo real de los costos asociados con la ejecución del proyecto.
- **Categorización de Gastos**: Organiza los gastos en categorías específicas, como materiales, mano de obra, equipamiento, etc.

## Requisitos del Sistema

- Java 17 o superior.
- Gradle para construir y ejecutar pruebas.

## Instalación

1. Clona el repositorio en tu máquina local:

   ```bash
   git clone [https://github.com/tuusuario/BudgetCalculator.git](https://github.com/Dyeseniact/BudgetCalculator)https://github.com/Dyeseniact/BudgetCalculator
   
2. Navega al directorio del proyecto:
   
   ```bash
   cd BudgetCalculator
   
3. Compila y ejecuta la aplicación:
   
   ```bash
   ./gradlew run

## Ejecutar pruebas

    ```bash
   ./gradlew test

## Análisis con SonarQube

   ```bash
   ./gradlew sonarqube -Dsonar.projectKey=BudgetCalculator -Dsonar.host.url=http://localhost:9000 -Dsonar.login=token-de-autenticacion

