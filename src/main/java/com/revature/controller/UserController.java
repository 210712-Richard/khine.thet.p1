package com.revature.controller;

import io.javalin.http.Context;

public interface UserController {
	
	void login(Context ctx);

	void logout(Context ctx);

}
