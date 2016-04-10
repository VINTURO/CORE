/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.notifications;

public enum Event {
    HUE_BRIDGE_PUSHLINKING_REQUIRED,
    HUE_BRIDGE_PUSHLINKING_SUCCESS,
    HUE_LAMP_STATE_UPDATED,
    HUE_BRIDGE_SCAN_FINISHED,
    ROOM_HAS_BEEN_DELETED,
    ROOM_HAS_BEEN_CREATED,
    LIGHT_HAS_BEEN_CREATED,
    LIGHT_HAS_BEEN_DELETED,
    BEACON_HAS_BEEN_CREATED,
    BEACON_HAS_BEEN_DELETED,
    BEACON_HAS_BEEN_HOOK,
    GROUP_HAS_BEEN_CREATED,
    LIGHT_HAS_BEEN_ATTACHED_TO_ROOM,
    BEACON_HAS_BEEN_ATTACHED_TO_ROOM,
    USER_HAS_BEEN_CREATED,
    USER_HAS_BEEN_DELETED,
    RULE_HAS_BEEN_CREATED, RULE_HAS_BEEN_DELETED, GROUP_HAS_BEEN_DELETED
}
