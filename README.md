# CodeDagger2
Dagger 2 Sample Application - ViewsCounter


# What is a dependency?
a dependency is a coupling between two modules of our code (in oriented object languages, two classes),
 usually because one of them uses the other to do something.

# Why dependencies are dangerous?
 if we need to change one module with another,
 we necessarily need to modify the code of the coupled class. That’s really bad programming practice.

Initial Code-
public class ClassA{

   private ClassB objB;

   public ClassA(){//Line 1
      objB = new ClassB();//Line 2
   }

   public void doSomething(){//Line 3
      ...
	  new ClassA().otherOperation();//Line 4
      objB.doSomethingElse();
      ...
   }
}
Requirement-
Suppose if we need to pass some values to ClassB this time,
then we also forced to change constructor of ClassA to get those values i.e. ClassB is
dangerously dependent on ClassA.
To fit the requirement now need to change Line 1,2,3,4..

Final Code-

public class ClassA{

   private ClassB objB;

   public ClassA(String bValue1,String bValue2){//Line 1
      objB = new ClassB(String bValue1,String bValue2);//Line 2
   }
	public void doSomething(String bValue1,String bValue2){//Line 3
      ...
	  new ClassA(bValue1,bValue2).otherOperation();//Line 4
      objB.doSomethingElse();
      ...
   }
}

# How to solve it? Answer: Dependency inversion
 Dependency inversion consists of passing dependencies (inject them) via constructor in order to
 extract the task of creating modules out from other modules.
in simple words, don't create object in other class instead pass the reference of your class to
other class' constructor.

# What is Dagger2?
-Dagger 2 is a fork from Dagger 1 under heavy development by Google, currently version 2.0.
-Dagger is dependency injector which uses a pre-compiler that creates all the classes it needs to work.
-The basic idea behind Dagger 2, was to make problems solvable by using code generation, hand written code,
as if we were writing all the code.
-Dagger 2 walks through the dependency graph and generates code that is both easy to understand and trace,
 while also saving you from writing the large amount of boilerplate code you
would normally need to write by hand to obtain references and pass them to other objects as dependencies.

Shut up and show me the code!:
# Dagger2 Implementation:

  build.gradle addition-
	 compile 'com.squareup.dagger:dagger:1.2.+'
    provided 'com.squareup.dagger:dagger-compiler:1.2.+'

 It creates precompiled required classes in order to be able to inject dependencies.
 As we only need it to compile the project, and won’t be used by application,
 we mark it as provided so that it isn’t included in final apk.


# Say hello to your first module:
 Modules are classes that provide instances of the objects we will need to inject.
 They are defined by annotating the class with @Module.

Android Code:

@Module
public class MyAppModule {

    Application mApplication;

    public MyAppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}


# Explanation:
@Module : identify this class as a Dagger module.
@Provides: Identify method as an injection provider.
@Singleton : returns a unique instance.



# Rest of the tutorial Updated Soon..