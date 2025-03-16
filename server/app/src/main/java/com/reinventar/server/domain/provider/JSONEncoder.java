package com.reinventar.server.domain.provider;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.reinventar.server.core.errors.CriticalError;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.Session;

public class JSONEncoder {
    public static Session decodeJson(String json) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json);

            Session session = new Session(
                (long) obj.get("id"),
                (String) obj.get("name"),
                Permissions.fromInt(((Long) obj.get("permission")).intValue()),
                (long) obj.get("created_at"),
                (long) obj.get("updated_at"),
                (long) obj.get("updated_at"),
                (long) obj.get("expires_at")
            );

            return session;
        } catch (ParseException exception) {
           throw new CriticalError.JSONParseError(exception); 
        } catch (ClassCastException exception) {
            throw new CriticalError.JSONParseError(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public static String encodeSession(Session session) {
        JSONObject sessionJsonObject = new JSONObject();

        sessionJsonObject.put("id", session.id);
        sessionJsonObject.put("name", session.name);
        sessionJsonObject.put("permission", session.permission.getPermissionLevel());
        sessionJsonObject.put("created_at", session.createdAt);
        sessionJsonObject.put("updated_at", session.updatedAt);
        sessionJsonObject.put("expires_at", session.expiresAt);
        sessionJsonObject.put("issued_at", session.issuedAt);

        return sessionJsonObject.toJSONString();
    }
}
