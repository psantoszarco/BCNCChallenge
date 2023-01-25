package com.inditex.ecommerce.architeture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;


@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.inditex.ecommerce")
public class ArchitectureTest {
    @ArchTest
    public static final ArchRule domain_are_free_of_circles =
            slices().matching("..domain.(**)..")
            .should().beFreeOfCycles();

    @ArchTest
    public static final ArchRule controllers_should_be_suffixed = classes()
            .that().resideInAPackage("..controller..")
            .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    public static final ArchRule repositories_should_located_in_repository = classes()
            .that().areAnnotatedWith(Repository.class)
            .should().resideInAPackage("..repository..");

    @ArchTest
    public static final ArchRule domain_not_access_the_controller_and_usecases =
            noClasses().that().resideInAPackage("..domain..")
                    .should().accessClassesThat()
                    .resideInAnyPackage("..controller..", "..usecase..");


    @ArchTest
    public static final ArchRule usecases_not_access_controllers =
            noClasses().that().resideInAPackage("..usecase..")
                    .should().accessClassesThat()
                    .resideInAPackage("..controller..");
}
