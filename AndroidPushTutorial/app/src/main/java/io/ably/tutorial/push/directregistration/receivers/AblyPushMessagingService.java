package io.ably.tutorial.push.directregistration.receivers;

import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import io.ably.lib.push.ActivationContext;
import io.ably.lib.types.RegistrationToken;

/**
 *
 */
public class AblyPushMessagingService extends FirebaseMessagingService {
    public static final String PUSH_NOTIFICATION_ACTION = AblyPushMessagingService.class.getName() + ".PUSH_NOTIFICATION_MESSAGE";
    
    @Override
    public void onMessageReceived(RemoteMessage message) {
        //FCM data is received here.
        Intent intent = new Intent(PUSH_NOTIFICATION_ACTION);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
    
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        //Store token in Ably
        ActivationContext.getActivationContext(this).onNewRegistrationToken(RegistrationToken.Type.FCM, s);
    }
}
