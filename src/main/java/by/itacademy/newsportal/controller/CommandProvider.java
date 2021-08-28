package by.itacademy.newsportal.controller;

import java.util.HashMap;
import java.util.Map;

import by.itacademy.newsportal.controller.impl.AddNews;
import by.itacademy.newsportal.controller.impl.AllNews;
import by.itacademy.newsportal.controller.impl.ApprovedNews;
import by.itacademy.newsportal.controller.impl.ChangeLocale;
import by.itacademy.newsportal.controller.impl.DeleteNews;
import by.itacademy.newsportal.controller.impl.GetDeletedNews;
import by.itacademy.newsportal.controller.impl.GetDisapprovedNews;
import by.itacademy.newsportal.controller.impl.GetUserNews;
import by.itacademy.newsportal.controller.impl.GoToAddNewsPage;
import by.itacademy.newsportal.controller.impl.GoToAdminPage;
import by.itacademy.newsportal.controller.impl.GoToAuthorizationPage;
import by.itacademy.newsportal.controller.impl.GoToCategoryPage;
import by.itacademy.newsportal.controller.impl.GoToMainPage;
import by.itacademy.newsportal.controller.impl.GoToOneNewsPage;
import by.itacademy.newsportal.controller.impl.GoToProfileUserPage;
import by.itacademy.newsportal.controller.impl.GoToRegistrationPage;
import by.itacademy.newsportal.controller.impl.GoToUpdateNewsPage;
import by.itacademy.newsportal.controller.impl.LogOut;
import by.itacademy.newsportal.controller.impl.RegistrationNewUser;
import by.itacademy.newsportal.controller.impl.SignIn;
import by.itacademy.newsportal.controller.impl.UnknownCommand;
import by.itacademy.newsportal.controller.impl.UpdateNews;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUser());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
		commands.put(CommandName.SIGNIN, new SignIn());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
		commands.put(CommandName.LOGOUT, new LogOut());
		commands.put(CommandName.GO_TO_PROFILE_USER_PAGE, new GoToProfileUserPage());
		commands.put(CommandName.ADDNEWS, new AddNews());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPage());
		commands.put(CommandName.GO_TO_ADDNEWS_PAGE, new GoToAddNewsPage());
		commands.put(CommandName.GO_TO_CATEGORY_PAGE, new GoToCategoryPage());
		commands.put(CommandName.GO_TO_ONE_NEWS_PAGE, new GoToOneNewsPage());
	    commands.put(CommandName.GET_DISAPPROVED_NEWS, new GetDisapprovedNews());
	    commands.put(CommandName.APPROVED_OF_NEWS, new ApprovedNews());
	    commands.put(CommandName.GET_USER_NEWS, new GetUserNews ());
	    commands.put(CommandName.GO_TO_UPDATE_NEWS_PAGE, new GoToUpdateNewsPage());
	    commands.put(CommandName.UPDATE_NEWS, new UpdateNews());
	    commands.put(CommandName.ALL_NEWS, new AllNews());
	    commands.put(CommandName.DELETE_NEWS, new DeleteNews());
	    commands.put(CommandName.GET_DELETED_NEWS, new GetDeletedNews());
	    
	
	} 
	
	public Command findCommand(String name) {
		if (name == null) {
			name = CommandName.UNKNOWN_COMMAND.toString();
		}
		
		CommandName commandName;
		try {
		    commandName = CommandName.valueOf(name.toUpperCase());
		}catch(IllegalArgumentException e) {
			// logging
			commandName = CommandName.UNKNOWN_COMMAND;	
		}
		
		Command command = commands.get(commandName);
		return command;
	}

}
