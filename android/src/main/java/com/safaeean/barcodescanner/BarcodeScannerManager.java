package com.safaeean.barcodescanner;

import android.view.View;

import javax.annotation.Nullable;
package com.safaeean.barcodescanner.BarcodeFragment;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class BarcodeScannerManager extends ViewGroupManager<BarcodeScannerView> implements LifecycleEventListener {
    private static final String REACT_CLASS = "RNBarcodeScannerView";

    private static final String DEFAULT_TORCH_MODE = "off";
    private static final String DEFAULT_CAMERA_TYPE = "back";

    private BarcodeScannerView mScannerView;
    private boolean mScannerViewVisible;

    public BarcodeScannerManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "cameraType")
    public void setCameraType(BarcodeScannerView view, @Nullable String cameraType) {
      if (cameraType != null) {
          view.setCameraType(cameraType);
      }
    }

    @ReactProp(name = "torchMode")
    public void setTorchMode(BarcodeScannerView view, @Nullable String torchMode) {
        if (torchMode != null) {
            view.setFlash(torchMode.equals("on"));
        }
    }

    @Override
    public BarcodeScannerView createViewInstance(ThemedReactContext context) {
        context.addLifecycleEventListener(this);
        mScannerView = new BarcodeScannerView(context);
        mScannerView.setCameraType(DEFAULT_CAMERA_TYPE);
        mScannerView.setFlash(DEFAULT_TORCH_MODE.equals("on"));
        mScannerViewVisible = true;
        return mScannerView;
    }

    @Override
    public void onHostResume() {
        mScannerView.onResume();
    }

    @Override
    public void onHostPause() {
        mScannerView.onPause();
    }

    @Override
    public void onHostDestroy() {
        mScannerView.stopCamera()
    }


//   /**
//    * Return a FrameLayout which will later hold the Fragment
//    */
//   @Override
//   public FrameLayout createViewInstance(ThemedReactContext reactContext) {
//     return new FrameLayout(reactContext);
//   }

//   /**
//    * Map the "create" command to an integer
//    */
//   @Nullable
//   @Override
//   public Map<String, Integer> getCommandsMap() {
//     return MapBuilder.of("create", COMMAND_CREATE);
//   }

//   /**
//    * Handle "create" command (called from JS) and call createFragment method
//    */
//   @Override
//   public void receiveCommand(
//     @NonNull FrameLayout root,
//     String commandId,
//     @Nullable ReadableArray args
//   ) {
//     super.receiveCommand(root, commandId, args);
//     int reactNativeViewId = args.getInt(0);
//     int commandIdInt = Integer.parseInt(commandId);

//     switch (commandIdInt) {
//       case COMMAND_CREATE:
//         createFragment(root, reactNativeViewId);
//         break;
//       default: {}
//     }
//   }

//   @ReactPropGroup(names = {"width", "height"}, customType = "Style")
//   public void setStyle(FrameLayout view, int index, Integer value) {
//     if (index == 0) {
//       propWidth = value;
//     }

//     if (index == 1) {
//       propHeight = value;
//     }
//   }







//     @Override
//     public void addView(BarcodeScannerView parent, View child, int index) {
//         parent.addView(child, index + 1);   // index 0 for camera preview reserved
//     }

//       public void createFragment(FrameLayout root, int reactNativeViewId) {
//     ViewGroup parentView = (ViewGroup) root.findViewById(reactNativeViewId);
//     setupLayout(parentView);

//     final MyFragment myFragment = new BardcodeFragment();
//     FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();
//     activity.getSupportFragmentManager()
//             .beginTransaction()
//             .replace(reactNativeViewId, myFragment, String.valueOf(reactNativeViewId))
//             .commit();
//   }

//   public void setupLayout(View view) {
//     Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
//       @Override
//       public void doFrame(long frameTimeNanos) {
//         manuallyLayoutChildren(view);
//         view.getViewTreeObserver().dispatchOnGlobalLayout();
//         Choreographer.getInstance().postFrameCallback(this);
//       }
//     });
//   }

//   /**
//    * Layout all children properly
//    */
//   public void manuallyLayoutChildren(View view) {
//       // propWidth and propHeight coming from react-native props
//       int width = propWidth;
//       int height = propHeight;

//       view.measure(
//               View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
//               View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));

//       view.layout(0, 0, width, height);
//   }
}
