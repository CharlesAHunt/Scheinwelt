package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Profile extends Controller {

    public static Result index() {
        return ok(profile.render());
    }

}
