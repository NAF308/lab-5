package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryUserDataAccessObject;
import data_access.RecipeDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipe_details.RecipeDetailsViewModel;
import interface_adapter.recipe_review.RecipeReviewController;
import interface_adapter.recipe_review.RecipeReviewPresenter;
import interface_adapter.recipe_search.RecipeSearchController;
import interface_adapter.recipe_search.RecipeSearchPresenter;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import interface_adapter.saved_recipes.SavedrecipesController;
import interface_adapter.saved_recipes.SavedrecipesPresenter;
import interface_adapter.saved_recipes.SavedrecipesViewModel;
import interface_adapter.search_results.SearchResultsController;
import interface_adapter.search_results.SearchResultsPresenter;
import interface_adapter.search_results.SearchResultsViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchInteractor;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.review_recipe.RecipeReviewInteractor;
import use_case.review_recipe.RecipeReviewOutputBoundary;
import use_case.saved_recipes.SavedRecipeInputBoundary;
import use_case.saved_recipes.SavedRecipeInteractor;
import use_case.saved_recipes.SavedRecipeOutputBoundry;
import use_case.search_results.SearchResultsInputBoundary;
import use_case.search_results.SearchResultsInteractor;
import use_case.search_results.SearchResultsOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.ForgotPasswordView;
import view.LoginView;
import view.ProfileView;
import view.RecipeDetailsView;
import view.RecipeSearchView;
import view.SavedrecipesView;
import view.SearchResultsView;
import view.SignupView;
import view.ViewManager;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final RecipeDataAccessObject recipeDataAccessObject = new RecipeDataAccessObject();

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private ProfileViewModel profileViewModel;
    private ProfileView profileView;
    private RecipeSearchViewModel recipeSearchViewModel;
    private RecipeSearchView recipeSearchView;
    private SearchResultsView searchResultsView;
    private SearchResultsViewModel searchResultsViewModel;
    private LoginView loginView;
    private ForgotPasswordView forgotPasswordView;
    private SavedrecipesViewModel savedrecipesViewModel;
    private RecipeDetailsViewModel recipeDetailsViewModel;
    private RecipeDetailsView recipeDetailsView;
    private SavedrecipesView savedrecipesView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addProfileView() {
        profileViewModel = new ProfileViewModel();
        profileView = new ProfileView(profileViewModel);
        cardPanel.add(profileView, profileView.getViewName());
        return this;
    }

    /**
     * Adds the Saved recipe View to the application.
     * @return this builder
     */
    public AppBuilder addSavedRecipesView() {
        savedrecipesViewModel = new SavedrecipesViewModel();
        savedrecipesView = new SavedrecipesView(savedrecipesViewModel);
        cardPanel.add(savedrecipesView, savedrecipesView.getViewName());
        return this;
    }

    /**
     * Adds the Search Recipe View to the application.
     * @return this builder
     */
    public AppBuilder addRecipeSearchView() {
        recipeSearchViewModel = new RecipeSearchViewModel();
        recipeSearchView = new RecipeSearchView(recipeSearchViewModel);
        cardPanel.add(recipeSearchView, recipeSearchView.getViewName());
        return this;
    }

    /**
     * Adds the Search Results View to the application.
     * @return this builder
     */
    public AppBuilder addSearchResultsView() {
        searchResultsViewModel = new SearchResultsViewModel();
        searchResultsView = new SearchResultsView(searchResultsViewModel);
        cardPanel.add(searchResultsView, searchResultsView.getViewName());
        return this;
    }

    /**
     * Adds the Recipe Details View to the application.
     * @return this builder
     */
    public AppBuilder addRecipeDetailsView() {
        recipeDetailsViewModel = new RecipeDetailsViewModel();
        recipeDetailsView = new RecipeDetailsView(recipeDetailsViewModel);
        cardPanel.add(recipeDetailsView, recipeDetailsView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Profile Use Case to the application.
     * @return this builder
     */
    public AppBuilder addProfileUseCase() {
        final ProfileOutputBoundary profileOutputBoundary = new ProfilePresenter(savedrecipesViewModel,
                viewManagerModel, recipeSearchViewModel);
        final ProfileInputBoundary userProfileInteractor = new ProfileInteractor(profileOutputBoundary,
                userDataAccessObject);

        final ProfileController controller = new ProfileController(userProfileInteractor);
        profileView.setProfileController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                profileViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the saved recipes Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSavedrecipesUseCase() {
        final SavedRecipeOutputBoundry savedRecipeOutputBoundry = new SavedrecipesPresenter(savedrecipesViewModel,
                profileViewModel, viewManagerModel);
        final SavedRecipeInputBoundary userSavedRecipeInteractor = new SavedRecipeInteractor(savedRecipeOutputBoundry);

        final SavedrecipesController savedrecipesController = new SavedrecipesController(userSavedRecipeInteractor);
        savedrecipesView.setSavedrecipesController(savedrecipesController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(profileViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        profileView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                profileViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        profileView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Search Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchUseCase() {
        final RecipeSearchOutputBoundary recipeSearchOutputBoundary = new RecipeSearchPresenter(
                recipeSearchViewModel, searchResultsViewModel, viewManagerModel, profileViewModel);
        final RecipeSearchInputBoundary searchInteractor = new RecipeSearchInteractor(
                recipeDataAccessObject, recipeSearchOutputBoundary);

        final RecipeSearchController controller = new RecipeSearchController(searchInteractor);
        recipeSearchView.setSearchController(controller);
        return this;
    }

    /**
     * Adds the Search Results Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchResultsUseCase() {
        final SearchResultsOutputBoundary searchResultsOutputBoundary = new SearchResultsPresenter(
                searchResultsViewModel, recipeDetailsViewModel, viewManagerModel, recipeSearchViewModel);
        final SearchResultsInputBoundary resultsInteractor = new SearchResultsInteractor(searchResultsOutputBoundary,
                userDataAccessObject);

        final SearchResultsController controller = new SearchResultsController(resultsInteractor);
        searchResultsView.setSearchResultsController(controller);
        return this;
    }

    /**
     * Adds the Recipe Review Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRecipeReviewUseCase() {
        final RecipeReviewOutputBoundary recipeReviewOutputBoundary = new RecipeReviewPresenter(viewManagerModel,
                profileViewModel);
        final RecipeReviewInteractor recipeReviewInteractor = new RecipeReviewInteractor(recipeReviewOutputBoundary,
                userDataAccessObject);

        final RecipeReviewController controller = new RecipeReviewController(recipeReviewInteractor);
        recipeDetailsView.setRecipeReviewController(controller);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Recipe Management");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
