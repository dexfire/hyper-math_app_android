package work.mathwiki.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import work.mathwiki.core.logger.Logger;

public class BaseFragment extends Fragment {

    @NonNull
    @Override
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle savedInstanceState) {
        Logger.si("onGetLayoutInflater");
        return super.onGetLayoutInflater(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.si("onAttach(Context content) ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.si(" onCreate(@Nullable Bundle savedInstanceState)");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.si("onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.si("onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.si("onActivityCreated(@Nullable Bundle savedInstanceState)");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.si("onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.si("onResume()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.si("onSaveInstanceState(@NonNull Bundle outState)");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.si("onPause() ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.si("onStop() ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.si("onDestroyView() ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.si("onDestroy() ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.si("onDetach() ");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Logger.si("onCreateOptionsMenu(Menu menu, MenuInflater inflater) ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.si("onOptionsItemSelected(MenuItem item)");
        return super.onOptionsItemSelected(item);
    }
}
