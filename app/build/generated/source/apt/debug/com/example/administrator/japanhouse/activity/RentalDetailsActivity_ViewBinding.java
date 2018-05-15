// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RentalDetailsActivity_ViewBinding implements Unbinder {
  private RentalDetailsActivity target;

  @UiThread
  public RentalDetailsActivity_ViewBinding(RentalDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RentalDetailsActivity_ViewBinding(RentalDetailsActivity target, View source) {
    this.target = target;

    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.act_rental_details_title, "field 'tvTitle'", TextView.class);
    target.tvRentalState = Utils.findRequiredViewAsType(source, R.id.act_rental_details_rentalState, "field 'tvRentalState'", TextView.class);
    target.ltRentalState = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_rentalState, "field 'ltRentalState'", LinearLayout.class);
    target.tvRefuseReason = Utils.findRequiredViewAsType(source, R.id.act_rental_details_refuseReason, "field 'tvRefuseReason'", TextView.class);
    target.ltRefuseReason = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_refuseReason, "field 'ltRefuseReason'", LinearLayout.class);
    target.tvLessorName = Utils.findRequiredViewAsType(source, R.id.act_rental_details_lessorName, "field 'tvLessorName'", TextView.class);
    target.ltLessorName = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_lessorName, "field 'ltLessorName'", LinearLayout.class);
    target.tvLessorPhone = Utils.findRequiredViewAsType(source, R.id.act_rental_details_lessorPhone, "field 'tvLessorPhone'", TextView.class);
    target.ltLessorPhone = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_lessorPhone, "field 'ltLessorPhone'", LinearLayout.class);
    target.tvLocation = Utils.findRequiredViewAsType(source, R.id.act_rental_details_location, "field 'tvLocation'", TextView.class);
    target.ltLoction = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_location, "field 'ltLoction'", LinearLayout.class);
    target.tvDistance = Utils.findRequiredViewAsType(source, R.id.act_rental_details_distance, "field 'tvDistance'", TextView.class);
    target.ltDistance = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_distance, "field 'ltDistance'", LinearLayout.class);
    target.tvFloor = Utils.findRequiredViewAsType(source, R.id.act_rental_details_floor, "field 'tvFloor'", TextView.class);
    target.ltFloor = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_floor, "field 'ltFloor'", LinearLayout.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.act_rental_details_area, "field 'tvArea'", TextView.class);
    target.ltArea = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_area, "field 'ltArea'", LinearLayout.class);
    target.tvPattern = Utils.findRequiredViewAsType(source, R.id.act_rental_details_pattern, "field 'tvPattern'", TextView.class);
    target.ltPattern = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_pattern, "field 'ltPattern'", LinearLayout.class);
    target.tvBathroom = Utils.findRequiredViewAsType(source, R.id.act_rental_details_bathroom, "field 'tvBathroom'", TextView.class);
    target.ltBathroom = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_bathroom, "field 'ltBathroom'", LinearLayout.class);
    target.tvToward = Utils.findRequiredViewAsType(source, R.id.act_rental_details_toward, "field 'tvToward'", TextView.class);
    target.ltToward = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_toward, "field 'ltToward'", LinearLayout.class);
    target.tvEquipment = Utils.findRequiredViewAsType(source, R.id.act_rental_details_equipment, "field 'tvEquipment'", TextView.class);
    target.ltEquipment = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_equipment, "field 'ltEquipment'", LinearLayout.class);
    target.roomPic = Utils.findRequiredViewAsType(source, R.id.act_rental_details_roomPic, "field 'roomPic'", GridView.class);
    target.ltRoomPic = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_roomPic, "field 'ltRoomPic'", LinearLayout.class);
    target.roomVideo = Utils.findRequiredViewAsType(source, R.id.act_rental_details_img, "field 'roomVideo'", ImageView.class);
    target.ltRoomVideo = Utils.findRequiredViewAsType(source, R.id.act_rental_details_liner_roomVideo, "field 'ltRoomVideo'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RentalDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tvRentalState = null;
    target.ltRentalState = null;
    target.tvRefuseReason = null;
    target.ltRefuseReason = null;
    target.tvLessorName = null;
    target.ltLessorName = null;
    target.tvLessorPhone = null;
    target.ltLessorPhone = null;
    target.tvLocation = null;
    target.ltLoction = null;
    target.tvDistance = null;
    target.ltDistance = null;
    target.tvFloor = null;
    target.ltFloor = null;
    target.tvArea = null;
    target.ltArea = null;
    target.tvPattern = null;
    target.ltPattern = null;
    target.tvBathroom = null;
    target.ltBathroom = null;
    target.tvToward = null;
    target.ltToward = null;
    target.tvEquipment = null;
    target.ltEquipment = null;
    target.roomPic = null;
    target.ltRoomPic = null;
    target.roomVideo = null;
    target.ltRoomVideo = null;
  }
}
