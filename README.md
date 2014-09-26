# LTU Mobile Android SDK

## Goals

This SDK has been developed to help you integrate LTU Image Recognition technology
in your Android applications. It aims at offering a high level of abstraction
of the LTU Cloud API in order to let you focus on the development of your application.

## Presentation

Reading [the API documentation](https://ltu2.ltutech.com/api/v1/) would be a
great start to grasp the concepts, vocabulary and examples of the API.

This SDK is organized in several packages. Package presentation - bottom-up approach:

  * resources
    * Contains all these classes mapping the API resources, these classes are
    basically data objects that will be retrieved from our server. They are called
    `ApiResourceData`.
  * client
    * Includes the `ApiClient` class - your entry point to the API. This class
    holds the methods to list resources, retrieves a single resource and creates
    new ones. "Low level" package and classes.
  * handlers
    * This package holds the response handler class hierarchy to help you
    manage the results retrieved from the API, should they be successful or
    on error.
  * utils
    * A couple of classes to make things easier. Checkout the `UrlImageDownloader`
    that allows you to download an image from its URL and set it as the
    `Drawable` of any `ImageView` in one single line !
  * requests
    * High level package to make things even easier. Each class is bound to a
    specific resource of the API and defines the exact methods that you will
    most probably need. That's probably where you should start.

## Getting started

To use this SDK you will need to add it as a library of your project, you will then be
able to use all provided classes as if they were your own code. To know how to
reference a library project please see the [Android documentation](http://developer.android.com/tools/projects/projects-eclipse.html#ReferencingLibraryProject).


Once you have setup the library you will need to prepare the username and password
you use to sign-in to LTU Cloud. The easiest way to get started is to use the
`requests` package.

One of the first thing you will probably want to do is send a `Query` on your
`Project`. For that you will need an image either retrieved from the gallery
or the camera, the source of the `Query` and its description, usually the name
and description of your application:

        List<Project> projects = new ArrayList<Project>();
        projects.add(new Project(X)); // X being the id of your project
        Bitmap image = ... // An image as a Bitmap retrieved from the camera or the gallery
        String source = "YourApplicationName";
        String source_description = "My Application can do this and that!";
        GenericResponseHandler<Query> = new GenericResponseHandler<Query>(Query.class){

                    @Override
                    public void onFailure(Throwable error, String message) {
                       // Inform the user of the error
                    }

                    @Override
                    protected void onResultReceived(Query result) {
                       // You retrieve the newly created Query
                       QueryStatus status = result.getStatus();
                       if(status.isError()){
                         // Something went wrong on our servers
                       } else {
                         // Retrieve the list of Match
                         List<Match> matches = result.getMatches();
                         // Do something with those Match
                       }
                    }
        };
        QueryRequests request = new QueryRequests(this.username, this.password);
        request.createQuery(image, projects, source, source_description, handler);

Explanations:

  * We prepare all the required parameters: the list `Project`s in which to search, the image
    to search and so on.
  * We then need to create a `GenericResponseHandler` to define how to handle
    the server response, should it be an error or a success. A `Query` that
    doesn't return any `Visual` is still considered successful. You should test
    the `QueryStatus` to know if there was a match.
    * You will have to implement `onResultReceived` to handle the `ApiResourceData` object
      returned by the API.
    * You can also override the following methods:
      * `onFailure`: in case something goes wrong during the request to the API
      * `onStart`: will be called before the request is sent
      * `onFinish`: called once the request is done, no matter the outcome
  * We then create and send the authenticated `QueryRequests` with the given
    parameters.


Once you have retrieved the newly created `Query` you could display the Visual
that matched your `Query` via:

        ArrayList<MatchedVisual> visualList = new ArrayList<MatchedVisual>();
        for (Match match : result.getMatches()) {
            visualList.add(match.getMatchedVisual());
        }
        // Display the list of `Visual` that matched your `Query` in your Activity

Or if you haven't found any `Match` you could try to add a new `Visual`. As
described in the [API documentation](https://ltu2.ltutech.com/api/v1/) `Visual`
belongs to one and only one `Project` therefore when creating a `Visual` you will
need to provide an instance of `Project`. As you have probably guessed by now you will
use a `VisualRequests` object to do the request and a `GenericResponseHandler`
to handle the created resource. To create a `Visual` you also need a title, an
image, other parameters are optional.

      Project yourCoolProject = ... // retrieved from the API
      Bitmap image = ... // The image that didn't match anything
      GenericResponseHandler<Visual> handler = new GenericResponseHandler<Visual>(Visual.class){
            @Override
            public void onFailure(Throwable error, String content) {
              // Inform user something went wrong
            }

           @Override
           protected void onResultReceived(Visual result) {
             // Do something with your newly created Visual
           }
      }
     VisualRequests request = new VisualRequests(this.username, this.password);
     List<Pair<String, String>> metadata = new ArrayList<Pair<String, String>>();
     metadata.add(new Pair<String, String>("url", "www.mydomain.com"));
     metadata.add(new Pair<String, String>("message", "My message"));
     request.createVisual(yourCoolProject, image, "My cool Visual title",
                          "My Visual name", metadata, handler);

Explanations:

  * Prepare the instance of `Project` to which you will add the `Visual`
  * Create a handler to manage the server response, it behaves just like the one
  you used to create the `Query`, except that it is specialized to handle `Visual`s
  * Prepare your authenticated `VisualRequests` and send the request to create
  a `Visual` entitled "My cool Visual" with some metadata

You have seen the basic features to use LTU Cloud from an Android application.
With these you should be able to cover most common use cases and provide a user
friendly image recognition experience.

### Easier way to do a search Query

To make it even easier to do a search Query the SDK contains a
`SearchQueryActivity`. You only need to extend and override the main
callbacks to be able to quickly and easily do a search Query. You will only need
to setup your UI, call the `setup` method (for the general parameters) and
finally call `startCamera` to start displaying the live video feed.

Before that, make sure to add those lines to your AndroidManifest.xml:

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-feature android:name="android.hardware.camera" />

### Advanced usage

#### ApiClient

The `ApiClient` class is in charge of doing the Http request to our API. It
has three main methods:

    * getResource/getResourceById: Retrieve a specific ApiResourceData
    * listResources: List all resources of a specific type
    * createResource: Create a top-level resource or a sub-resource

It is the class where all you have seen so far comes nicely together. You should
also take a look at `RequestParamsBuilder` and the `handlers` package.

If you use this class directly rather than the higher level `Requests` objects,
you will be able to cancel the requests. To be able to do so, you need to create
an `ApiClient` with a `Context`. If you create it in an `Activity` passing
`this` should be enough.

    // Somewhere in your activity code
    ApiClient client = new ApiClient(this,this.username, this.password);
    // Send request, handle response ...

    // Preferably in your activity onDestroy method
    client.cancelRequests(true); // cancel pending and active requests

Note: If you call `cancelRequests` on an `ApiClient` created without a `Context`
a `RuntimeException` will be thrown.

#### Camera

If you want your users to be able to search images he just took, you will need to
invoke the camera at some point. The easiest way to do it is to use the
[existing camera intent](http://developer.android.com/guide/topics/media/camera.html#intents).

This SDK also provides a `CameraActivity` that works pretty much the same way.
It also allows you to customize how the camera image live feed is displayed
and the format, size and other parameters of the captured image. For more
information on this you can see the [Android documentation](http://developer.android.com/guide/topics/media/camera.html#custom-camera) and
the source code in the package `camera`.

If you intend to use the camera provided by the SDK be sure to add the following
to your `AndroidManifest.xml`:

    <activity
            android:name="com.ltu.sdk.camera.CameraActivity"
            android:screenOrientation="portrait"
            android:theme="@style/FullScreenCameraStyle" >
    </activity>
